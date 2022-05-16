package dev.ivanqueiroz.transferscheduler.domain.factories;

import dev.ivanqueiroz.transferscheduler.domain.entities.TaxCalc;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.entities.impl.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public enum TransferType {
    A(TransferTypeA::new, new TransferATaxCalc()),
    B(TransferTypeB::new, new TransferBTaxCalc()),
    C(TransferTypeC::new, new TransferCTaxCalc());
    private final Supplier<Transfer> constructor;
    private final TaxCalc calc;
}
