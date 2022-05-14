package dev.ivanqueiroz.transferscheduler.domain.services;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferService {
  Transfer schedule(Transfer transfer);

  Page<Transfer> listSchedules(Pageable pageable);
}
