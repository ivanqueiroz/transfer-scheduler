package dev.ivanqueiroz.transferscheduler.domain.entities.factories;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.entities.TransferTypeA;
import dev.ivanqueiroz.transferscheduler.domain.entities.TransferTypeB;
import dev.ivanqueiroz.transferscheduler.domain.entities.TransferTypeC;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.InvalidDateIntervalException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransferTypeFactoryTest {

  @Test
  void getTransferTypeAFromTransferDate() {

    LocalDate todayTransfer = LocalDate.now();

    Transfer transfer = TransferTypeFactory.getTransfer(todayTransfer);

    assertTrue(transfer instanceof TransferTypeA);
  }

  @Test
  void getTransferTypeBFromTransferDate() {

    LocalDate tenDaysFromToday = LocalDate.now().plusDays(10);

    Transfer transfer = TransferTypeFactory.getTransfer(tenDaysFromToday);

    assertTrue(transfer instanceof TransferTypeB);
  }

  @Test
  void getTransferTypeCFromTransferDate() {

    LocalDate elevenDaysFromToday = LocalDate.now().plusDays(11);

    Transfer transfer = TransferTypeFactory.getTransfer(elevenDaysFromToday);

    assertTrue(transfer instanceof TransferTypeC);
  }

  @Test
  void getExceptionFromTransferDate() {
    LocalDate invalidDate = LocalDate.now().minusDays(2);

    assertThrows(InvalidDateIntervalException.class, () -> TransferTypeFactory.getTransfer(invalidDate));
  }
}
