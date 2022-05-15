package dev.ivanqueiroz.transferscheduler.domain.services.impl;

import dev.ivanqueiroz.transferscheduler.domain.entities.Account;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.entities.repositories.AccountRepository;
import dev.ivanqueiroz.transferscheduler.domain.entities.repositories.TransferRepository;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.BusinessRuleException;
import dev.ivanqueiroz.transferscheduler.domain.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private final TransferRepository transferRepository;
  private final AccountRepository accountRepository;

  @Override
  @Transactional
  public Transfer schedule(Transfer transfer) {
    validate(transfer);
    return transferRepository.save(transfer);
  }

  @Override
  public Page<Transfer> listSchedules(Pageable pageable) {
    return transferRepository.findAll(pageable);
  }

  private void validate(Transfer transfer) {
    if (transfer.getSource().getNumber().equals(transfer.getDestination().getNumber())) {
      throw new BusinessRuleException("Transfer should have different origin and destination");
    }
    this.validateAccount(transfer.getSource());
    this.validateAccount(transfer.getDestination());
  }

  private void validateAccount(Account account) {
    String accountNumber = account.getNumber();
    if (accountRepository.findById(accountNumber).isEmpty()) {
      throw new BusinessRuleException("Account not exist: %s".formatted(accountNumber));
    }
  }
}
