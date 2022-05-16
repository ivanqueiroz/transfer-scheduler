package dev.ivanqueiroz.transferscheduler.domain.services;

import dev.ivanqueiroz.transferscheduler.application.web.dto.TransferDto;
import dev.ivanqueiroz.transferscheduler.domain.entities.Account;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.factories.TransferTypeFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class TransferMockFactory {
    private TransferMockFactory() {
        throw new IllegalAccessError("Not permited");
    }


    public static TransferDto createMockInvalidTransferDto() {
        Transfer transfer = createMockTransfer();
        transfer.setAmount(null);
        return TransferDto.valueOf(transfer);
    }

    public static TransferDto createMockTransferDto() {
        return TransferDto.valueOf(createMockTransfer());
    }

    public static Transfer createMockTransfer() {
        Account sourceAccount = AccountMockFactory.createSourceAccount();
        Account destinationAccount = AccountMockFactory.createDestinationAccount();
        Transfer transfer = TransferTypeFactory.getTransfer(LocalDate.now().plusDays(1));
        transfer.setId(new Random().nextLong());
        transfer.setAmount(BigDecimal.TEN);
        transfer.setSource(sourceAccount);
        transfer.setDestination(destinationAccount);
        transfer.setTax(transfer.calculateTax());
        return transfer;
    }

    public static Transfer createTransferWithSameSourceDestination() {
        Account account = AccountMockFactory.createSourceAccount();
        Transfer transfer = TransferTypeFactory.getTransfer(LocalDate.now().plusDays(1));
        transfer.setAmount(BigDecimal.TEN);
        transfer.setSource(account);
        transfer.setDestination(account);
        return transfer;
    }
}
