package dev.ivanqueiroz.transferscheduler.domain.exceptions;

public class SameAccountException extends BusinessRuleException {
    public SameAccountException(String message) {
        super(message);
    }
}
