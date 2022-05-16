package dev.ivanqueiroz.transferscheduler.domain.exceptions;

public class AccountNotFoundException extends BusinessRuleException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
