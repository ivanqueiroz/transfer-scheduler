package dev.ivanqueiroz.transferscheduler.application.web.handlers;

import dev.ivanqueiroz.transferscheduler.application.web.dto.ExceptionDto;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.InvalidDateIntervalException;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.TransferNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ResponseBody
  @ExceptionHandler(TransferNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ExceptionDto accountNotFoundHandler(TransferNotFoundException ex) {
    log.error("Transfer not found: {}", ex.getLocalizedMessage(), ex);
    return new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getLocalizedMessage());
  }

  @ResponseBody
  @ExceptionHandler(InvalidDateIntervalException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ExceptionDto accountNotFoundHandler(InvalidDateIntervalException ex) {
    log.error("Unexpected date interval: {}", ex.getLocalizedMessage(), ex);
    return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getLocalizedMessage());
  }

  @ResponseBody
  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ExceptionDto handlerMethodThrowable(Throwable ex) {
    log.error("Internal error: {}", ex.getLocalizedMessage(), ex);
    return new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getLocalizedMessage());
  }

  @ResponseBody
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ExceptionDto bindigErrorsHandler(BindException ex) {
    log.error("Validation error: {}", ex.getLocalizedMessage(), ex);
    return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getLocalizedMessage());
  }

}
