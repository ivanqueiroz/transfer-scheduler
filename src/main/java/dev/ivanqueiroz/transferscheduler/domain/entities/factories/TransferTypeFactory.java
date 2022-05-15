package dev.ivanqueiroz.transferscheduler.domain.entities.factories;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.InvalidDateIntervalException;

import java.time.Duration;
import java.time.LocalDate;

public class TransferTypeFactory {

  private TransferTypeFactory() {
    throw new IllegalAccessError("Not permited.");
  }

  public static Transfer getTransfer(LocalDate transferDate) {
    TransferType transferType = transferTypeFromTransferDate(transferDate);
    Transfer transfer = transferType.getConstructor().get();
    transfer.setTransferDate(transferDate);
    transfer.setScheduleDate(LocalDate.now());
    transfer.setCalcTax(transferType.getCalc());
    return transfer;
  }

  private static TransferType transferTypeFromTransferDate(LocalDate transferDate) {
    if (transferDate.isBefore(LocalDate.now())) {
      throw new InvalidDateIntervalException("exception.transfer.rule3");
    }
    if (transferDate.isEqual(LocalDate.now())) {
      return TransferType.A;
    }
    if (Duration.between(LocalDate.now().atStartOfDay(), transferDate.atStartOfDay()).toDays() <= 10) {
      return TransferType.B;
    }
    if (Duration.between(LocalDate.now().atStartOfDay(), transferDate.atStartOfDay()).toDays() > 10) {
      return TransferType.C;
    }
    throw new InvalidDateIntervalException("exception.transfer.rule4");
  }
}
