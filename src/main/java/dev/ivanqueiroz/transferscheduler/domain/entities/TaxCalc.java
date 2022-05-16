package dev.ivanqueiroz.transferscheduler.domain.entities;

import java.math.BigDecimal;

public interface TaxCalc {

    BigDecimal calc(Transfer transfer);

}
