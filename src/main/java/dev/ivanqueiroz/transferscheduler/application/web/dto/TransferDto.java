package dev.ivanqueiroz.transferscheduler.application.web.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferDto(Long id, @NotNull BigDecimal amount, BigDecimal taxAmount, @FutureOrPresent LocalDate transferDate, @FutureOrPresent LocalDate scheduleDate, @NotBlank String accountOrigin,
                          @NotBlank String accountDestination) {
}
