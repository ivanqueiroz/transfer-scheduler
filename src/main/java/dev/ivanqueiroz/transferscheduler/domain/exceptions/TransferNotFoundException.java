package dev.ivanqueiroz.transferscheduler.domain.exceptions;

public class TransferNotFoundException extends BusinessRuleException {
  public TransferNotFoundException(String message) {
    super(message);
  }
}
