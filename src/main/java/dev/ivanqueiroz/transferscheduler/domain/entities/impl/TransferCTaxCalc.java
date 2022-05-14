package dev.ivanqueiroz.transferscheduler.domain.entities.impl;

import dev.ivanqueiroz.transferscheduler.domain.entities.TaxCalc;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.TaxException;

import java.math.BigDecimal;

public class TransferCTaxCalc implements TaxCalc {
  @Override
  public BigDecimal calc(Transfer transfer) {
    if (transfer.getDaysDiference() > 10 && transfer.getDaysDiference() <= 20) {
      return transfer.getAmount().multiply(BigDecimal.valueOf(0.08));
    }
    if (transfer.getDaysDiference() > 20 && transfer.getDaysDiference() <= 30) {
      return transfer.getAmount().multiply(BigDecimal.valueOf(0.06));
    }
    if (transfer.getDaysDiference() > 30 && transfer.getDaysDiference() <= 40) {
      return transfer.getAmount().multiply(BigDecimal.valueOf(0.04));
    }
    if (transfer.getDaysDiference() > 40 && transfer.getAmount().longValue() > 100000) {
      return transfer.getAmount().multiply(BigDecimal.valueOf(0.02));
    }
    throw new TaxException("Undefined tax for transfer type C");
  }
}
