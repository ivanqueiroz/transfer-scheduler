package dev.ivanqueiroz.transferscheduler.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("T")
@MappedSuperclass
public abstract class Transfer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "amount")
  @Positive
  @Digits(fraction = 2, integer = 10, message = "msg2")
  private BigDecimal amount;

  @Column(name = "tax")
  @Positive
  @Digits(fraction = 2, integer = 10, message = "msg2")
  private BigDecimal tax;

  @Column(columnDefinition = "DATE", name = "schedule_date")
  @NotNull
  @FutureOrPresent
  private LocalDate scheduleDate;

  @Column(columnDefinition = "DATE", name = "transfer_date")
  @FutureOrPresent
  @NotNull
  private LocalDate transferDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_origin_source")
  private Account source;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_destination_account")
  private Account destination;

  @Column(insertable = false, updatable = false)
  private String type;

  public long getDaysDiference() {
    return Duration.between(this.getTransferDate().atStartOfDay(), this.getScheduleDate().atStartOfDay()).toDays();
  }

}
