package dev.ivanqueiroz.transferscheduler.application.web.handlers;

import dev.ivanqueiroz.transferscheduler.application.web.dto.ExceptionDto;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.TransferNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    log.error("Transfer not found: {}", ex.getLocalizedMessage());
    return new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getLocalizedMessage());
  }

}
