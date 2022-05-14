package dev.ivanqueiroz.transferscheduler.domain.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "C")
public class TransferTypeC extends Transfer {
}
