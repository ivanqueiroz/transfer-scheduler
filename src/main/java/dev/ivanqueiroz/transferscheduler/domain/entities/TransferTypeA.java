package dev.ivanqueiroz.transferscheduler.domain.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "A")
public class TransferTypeA extends Transfer {

  public BigDecimal calcTax(TaxCalc taxCalc) {
    return taxCalc.calc(this);
  }

}
