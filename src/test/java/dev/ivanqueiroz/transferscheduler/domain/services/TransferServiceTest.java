package dev.ivanqueiroz.transferscheduler.domain.services;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.BusinessRuleException;
import dev.ivanqueiroz.transferscheduler.domain.repositories.AccountRepository;
import dev.ivanqueiroz.transferscheduler.domain.repositories.TransferRepository;
import dev.ivanqueiroz.transferscheduler.domain.services.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class TransferServiceTest {

    @MockBean
    private TransferRepository transferRepository;
    @MockBean
    private AccountRepository accountRepository;
    private TransferService transferService;

    @BeforeEach
    void setUp() {
        transferService = new TransferServiceImpl(transferRepository, accountRepository);
    }

    @Test
    @DisplayName("when source account is the same of destiny account, should throws exception")
    void sourceAndOriginAccountEquals() {
        var transfer = TransferMockFactory.createTransferWithSameSourceDestination();
        assertThrows(BusinessRuleException.class, () -> transferService.schedule(transfer));
    }

    @Test
    @DisplayName("when source account not found, should throws exception")
    void sourceAccountNotFound() {
        var transfer = TransferMockFactory.createMockTransfer();
        given(accountRepository.findById(transfer.getSource().getNumber())).willReturn(Optional.empty());
        assertThrows(BusinessRuleException.class, () -> transferService.schedule(transfer));
    }

    @Test
    @DisplayName("when destiny account not found, should throws exception")
    void destinyAccountNotFound() {
        var transfer = TransferMockFactory.createMockTransfer();
        given(accountRepository.findById(transfer.getSource().getNumber())).willReturn(Optional.of(transfer.getSource()));
        given(accountRepository.findById(transfer.getDestination().getNumber())).willReturn(Optional.empty());
        assertThrows(BusinessRuleException.class, () -> transferService.schedule(transfer));
    }

    @Test
    void listSchedules() {
        var pageRequest = PageRequest.of(0, 20);
        var list = List.of(TransferMockFactory.createMockTransfer());
        given(transferRepository.findAll(pageRequest)).willReturn(new PageImpl<>(list, pageRequest, list.size()));
        Page<Transfer> transfers = transferService.listSchedules(pageRequest);
        assertEquals(list.size(), transfers.toList().size());
    }
}
