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
    return transferTypeFromTransferDate(transferDate).getConstructor().get();
  }

  private static TransferType transferTypeFromTransferDate(LocalDate transferDate) {
    if (transferDate.isBefore(LocalDate.now())) {
      throw new InvalidDateIntervalException("Invalid Date interval");
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
    throw new InvalidDateIntervalException("Invalid Date interval");
  }
}
