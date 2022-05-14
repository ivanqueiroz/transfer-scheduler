package dev.ivanqueiroz.transferscheduler.domain.entities.impl;

import dev.ivanqueiroz.transferscheduler.domain.entities.TaxCalc;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;

import java.math.BigDecimal;

public class TransferBTaxCalc implements TaxCalc {
  @Override
  public BigDecimal calc(Transfer transfer) {
    if (transfer.getDaysDiference() == 0) {
      return BigDecimal.valueOf(12L);
    }
    return BigDecimal.valueOf(12L).multiply(BigDecimal.valueOf(transfer.getDaysDiference()));
  }
}
