package dev.ivanqueiroz.transferscheduler.domain.entities.impl;

import dev.ivanqueiroz.transferscheduler.domain.entities.TaxCalc;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;

import java.math.BigDecimal;

public class TransferATaxCalc implements TaxCalc {
  @Override
  public BigDecimal calc(Transfer transfer) {
    return BigDecimal.valueOf(3L).add(transfer.getAmount().multiply(BigDecimal.valueOf(0.03)));
  }
}
