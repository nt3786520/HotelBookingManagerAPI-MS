package com.cg.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "booking")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "guestName")
  @NotBlank(message = "Guest name cannot be blank")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Guest name must contain only letters and spaces")
  private String guestName;

  @Column(name = "roomNumber")
  @NotBlank(message = "room Number name cannot be blank")
  private int roomNumber;

  @Column(name = "date")
  @NotBlank(message = "date name cannot be blank")
  private LocalDate date;

  public Booking() {}

  public Booking(String guestName, int roomNumber, LocalDate date) {
    this.guestName = guestName;
    this.roomNumber = roomNumber;
    this.date = date;
  }

  @Override
  public String toString() {
    return "Booking{"
        + "id="
        + id
        + ", guestName='"
        + guestName
        + '\''
        + ", roomNumber="
        + roomNumber
        + ", date="
        + date
        + '}';
  }
}
