package dev.ivanqueiroz.transferscheduler.application.web.handlers;

import dev.ivanqueiroz.transferscheduler.application.web.dto.ExceptionDto;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.AccountNotFoundException;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.InvalidDateIntervalException;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.TransferNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionDto accountNotFoundHandler(AccountNotFoundException ex, Locale locale) {
        String message = returnLocalizedMessage(ex.getMessage(), locale);
        log.error("Account not found: {}", message, ex);
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), message);
    }

    @ResponseBody
    @ExceptionHandler(TransferNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionDto transferNotFoundHandler(TransferNotFoundException ex, Locale locale) {
        String message = returnLocalizedMessage(ex.getMessage(), locale);
        log.error("Transfer not found: {}", message, ex);
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), message);
    }

    @ResponseBody
    @ExceptionHandler(InvalidDateIntervalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExceptionDto invalidIntervaldHandler(InvalidDateIntervalException ex, Locale locale) {
        String message = returnLocalizedMessage(ex.getMessage(), locale);
        log.error("Unexpected date interval: {}", ex.getMessage(), ex);
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionDto handlerMethodThrowable(Throwable ex, Locale locale) {
        String message = returnLocalizedMessage(ex.getMessage(), locale);
        log.error("Internal error: {}", ex.getMessage(), ex);
        return new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ExceptionDto bindigErrorsHandler(MethodArgumentNotValidException ex, Locale locale) {
        List<String> errorMessages = ex.getAllErrors().stream().map(objectError -> messageSource.getMessage(objectError, locale)).toList();
        log.error("Validation error: {}", errorMessages, ex);
        return new ExceptionDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), errorMessages.toString());
    }

    private String returnLocalizedMessage(String key, Locale locale) {
        try {
            return messageSource.getMessage(key, null, locale);
        } catch (NoSuchMessageException ex) {
            log.error("Message not found");
            return key;
        }
    }

}
