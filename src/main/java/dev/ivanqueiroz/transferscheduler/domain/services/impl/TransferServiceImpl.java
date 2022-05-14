package dev.ivanqueiroz.transferscheduler.domain.services.impl;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.entities.repositories.AccountRepository;
import dev.ivanqueiroz.transferscheduler.domain.entities.repositories.TransferRepository;
import dev.ivanqueiroz.transferscheduler.domain.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

  private final TransferRepository transferRepository;
  private final AccountRepository accountRepository;

  @Override
  public Transfer schedule(Transfer transfer) {
    return null;
  }

  @Override
  public Page<Transfer> listSchedules(Pageable pageable) {
    return transferRepository.findAll(pageable);
  }
}
