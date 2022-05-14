package dev.ivanqueiroz.transferscheduler.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

  @Id
  @Max(20)
  private String number;

  @Column(name = "person")
  @NotBlank
  @Max(50)
  private String person;

  @Column(name = "document")
  @NotBlank
  @Max(20)
  private String document;

}
