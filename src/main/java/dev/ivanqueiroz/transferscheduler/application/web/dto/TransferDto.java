package dev.ivanqueiroz.transferscheduler.application.web.dto;

import dev.ivanqueiroz.transferscheduler.domain.entities.Account;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.entities.factories.TransferTypeFactory;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferDto(Long id, @NotNull(message = "{amount.notnull}") BigDecimal amount, BigDecimal taxAmount, @FutureOrPresent LocalDate transferDate, @FutureOrPresent LocalDate scheduleDate,
                          @NotBlank String accountSource, @NotBlank String accountDestination) {
  public Transfer toTransfer() {
    Transfer transfer = TransferTypeFactory.getTransfer(this.transferDate);
    transfer.setAmount(this.amount);
    transfer.setTransferDate(this.transferDate);
    transfer.setSource(new Account(this.accountSource, null, null));
    transfer.setDestination(new Account(this.accountDestination, null, null));
    transfer.setTax(transfer.calculateTax());
    return transfer;
  }

  public static TransferDto valueOf(Transfer transfer) {
    return new TransferDto(transfer.getId(), transfer.getAmount(), transfer.getTax(), transfer.getTransferDate(), transfer.getScheduleDate(), transfer.getSource().getNumber(),
      transfer.getDestination().getNumber());
  }
}
