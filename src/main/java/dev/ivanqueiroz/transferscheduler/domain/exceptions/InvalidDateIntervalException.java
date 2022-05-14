package dev.ivanqueiroz.transferscheduler.domain.exceptions;

public class InvalidDateIntervalException extends BusinessRuleException {
  public InvalidDateIntervalException(String message) {
    super(message);
  }
}
