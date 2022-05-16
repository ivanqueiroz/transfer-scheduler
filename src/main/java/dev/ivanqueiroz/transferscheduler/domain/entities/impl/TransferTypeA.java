package dev.ivanqueiroz.transferscheduler.domain.entities.impl;

import dev.ivanqueiroz.transferscheduler.domain.entities.TaxCalc;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "A")
public class TransferTypeA extends Transfer {

    @Transient
    private TaxCalc taxCalc;

    @Override
    public void setCalcTax(TaxCalc taxCalc) {
        this.taxCalc = taxCalc;
    }

    @Override
    public BigDecimal calculateTax() {
        return taxCalc.calc(this);
    }
}
