package dev.ivanqueiroz.transferscheduler.domain.entities;

import dev.ivanqueiroz.transferscheduler.domain.entities.factories.TransferTypeFactory;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.TaxException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculationTests {

  @Test
  void calcATaxFromTodayTransfer() {
    LocalDate todayTransfer = LocalDate.now();
    Transfer transfer = TransferTypeFactory.getTransfer(todayTransfer);
    transfer.setAmount(new BigDecimal("100"));
    BigDecimal value = transfer.calculateTax();
    assertEquals(new BigDecimal("6.00"), value);
  }

  @ParameterizedTest(name = "{index} => daysToAdd={0}, amount={1}, expected={2}")
  @CsvSource({"10, 100, 120", "11, 100, 8.00", "21, 100, 6.00", "31, 100, 4.00", "41, 100001, 2000.02"})
  void calcCTaxForScheduledTransferInNDays(int daysToAddm, String amount, String expected) {
    LocalDate elevenDays = LocalDate.now().plusDays(daysToAddm);
    Transfer transfer = TransferTypeFactory.getTransfer(elevenDays);
    transfer.setAmount(new BigDecimal(amount));
    BigDecimal value = transfer.calculateTax();
    assertEquals(new BigDecimal(expected), value);
  }

  @Test
  void calcCTaxForScheduledTransferIn41DaysAndNotEnoughtAmount() {
    LocalDate elevenDays = LocalDate.now().plusDays(41);
    Transfer transfer = TransferTypeFactory.getTransfer(elevenDays);
    transfer.setAmount(new BigDecimal("100"));

    assertThrows(TaxException.class, transfer::calculateTax);
  }

  @Test
  void calcCTaxForScheduledTransferIn41DaysAndNotAmount() {
    LocalDate elevenDays = LocalDate.now().plusDays(41);
    Transfer transfer = TransferTypeFactory.getTransfer(elevenDays);

    assertThrows(TaxException.class, transfer::calculateTax);
  }
}
