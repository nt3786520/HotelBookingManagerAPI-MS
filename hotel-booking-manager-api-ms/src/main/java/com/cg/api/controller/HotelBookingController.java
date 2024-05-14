package com.cg.api.controller;

import com.cg.api.exception.ResourceNotFoundException;
import com.cg.api.model.Booking;
import com.cg.api.repository.BookingManagerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
@RestController
@RequestMapping("/api")
public class HotelBookingController {

  private final BookingManagerRepository managerRepository;

  @Autowired
  public HotelBookingController(BookingManagerRepository managerRepository) {
    this.managerRepository = managerRepository;
  }

  /**
   * @Narendra Thakur POST API book the hotel for Guest.
   *
   * @param booking
   * @return Booking details
   */
  @Operation(
      summary = "Create hotel Bookings for room.",
      description = "Booking consists of a guest name, a room number, and a date.")
  @PostMapping(
      value = "/bookings",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
    log.info("Hotel booking request process for client:");
    Booking holtelbooking = null;
    try {
      holtelbooking =
          managerRepository.save(
              new Booking(booking.getGuestName(), booking.getRoomNumber(), booking.getDate()));
      log.debug("Hotel booking successful for requested client!!...", holtelbooking);
      return new ResponseEntity<>(holtelbooking, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * @Narendra Thakur GET API to Find the available rooms on a given date.
   *
   * @param date
   * @return Booking details
   */
  @Operation(
      summary = " Get available rooms on a given date",
      description = "Find the available rooms on a given date.")
  @GetMapping(path = "/available-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Booking>> getAllAvailRoomsByDate(
      @Parameter(description = "By using Date find the hotel room details.")
          @RequestParam(value = "date", required = false)
          LocalDate date) {
    log.info("Get all available Rooms By Date:" + date);
    try {
      List<Booking> bookings = new ArrayList<>();

      if (date == null) managerRepository.findAll().forEach(bookings::add);
      else managerRepository.findByDate(date).forEach(bookings::add);

      if (bookings.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(bookings, HttpStatus.OK);
    } catch (IllegalArgumentException ex) {
      log.error("Booking not found for given date.", ex.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * @Narendra Thakur GET API to Find the available rooms on a given Guest name.
   *
   * @param guestName
   * @return Booking details
   */
  @Operation(
      summary = " Get available rooms on a given guest name.",
      description = "Find the available rooms on a given guest name.")
  @GetMapping(path = "/bookings-for-guest", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Booking>> getAllAvailBookingForGuest(
      @Parameter(description = "by using Guest Name find the room details.")
          @RequestParam(value = "guestName", required = false)
          String guestName) {
    try {
      log.info("Get all available room bookings-for-guest");
      List<Booking> booking = new ArrayList<>();

      if (guestName == null) managerRepository.findAll().forEach(booking::add);
      else managerRepository.findByGuestNameContainingIgnoreCase(guestName).forEach(booking::add);
      if (booking.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(booking, HttpStatus.OK);
    } catch (IllegalArgumentException ex) {
      log.error("Booking not found for given guest.", ex.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * @Narendra Thakur GET API to Find the available rooms on a given Id.
   *
   * @param id
   * @return Booking details
   */
  @Operation(
          summary = " Get available rooms on a given auto genrated ID.",
          description = "Find the available rooms on a given ID.")
  @GetMapping(path = "/booking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Booking> getTutorialById(@PathVariable("id") long id) {
    Booking tutorial =
        managerRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found booking with id = " + id));

    return new ResponseEntity<>(tutorial, HttpStatus.OK);
  }
}
