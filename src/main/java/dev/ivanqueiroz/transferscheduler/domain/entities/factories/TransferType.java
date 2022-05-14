package dev.ivanqueiroz.transferscheduler.domain.entities.factories;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.entities.TransferTypeA;
import dev.ivanqueiroz.transferscheduler.domain.entities.TransferTypeB;
import dev.ivanqueiroz.transferscheduler.domain.entities.TransferTypeC;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public enum TransferType {
  A(TransferTypeA::new),
  B(TransferTypeB::new),
  C(TransferTypeC::new);
  private final Supplier<Transfer> constructor;
}
