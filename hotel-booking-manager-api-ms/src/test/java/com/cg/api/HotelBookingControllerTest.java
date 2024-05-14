package com.cg.api;

import com.cg.api.controller.HotelBookingController;
import com.cg.api.exception.ResourceNotFoundException;
import com.cg.api.model.Booking;
import com.cg.api.repository.BookingManagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
class HotelBookingControllerTest {

  @Mock private BookingManagerRepository managerRepository;

  @InjectMocks private HotelBookingController bookingController;

  private Booking sampleBooking;

  @BeforeEach
  void setUp() {
    sampleBooking = new Booking("John Doe", 101, LocalDate.now());
  }

  @Test
  void testCreateBooking_SuccessTest() {
    when(managerRepository.save(any(Booking.class))).thenReturn(sampleBooking);

    ResponseEntity<Booking> responseEntity = bookingController.createBooking(sampleBooking);

    Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
  }

  @Test
  void createBooking_InternalServerErrorTest() {
    when(managerRepository.save(any(Booking.class))).thenThrow(RuntimeException.class);

    ResponseEntity<Booking> responseEntity = bookingController.createBooking(sampleBooking);

    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  @Test
  void getAllAvailBookingForGuest_NoContentTest() {
    when(managerRepository.findByGuestNameContainingIgnoreCase(anyString()))
        .thenReturn(new ArrayList<>());

    ResponseEntity<List<Booking>> responseEntity =
        bookingController.getAllAvailBookingForGuest("John");

    Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  @Test
  void getAllAvailBookingForGuest_SuccessTest() {
    List<Booking> bookings = new ArrayList<>();
    bookings.add(sampleBooking);
    when(managerRepository.findByGuestNameContainingIgnoreCase(anyString())).thenReturn(bookings);

    ResponseEntity<List<Booking>> responseEntity =
        bookingController.getAllAvailBookingForGuest("John");

    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  void getTutorialById_SuccessTest() {
    when(managerRepository.findById(anyLong())).thenReturn(Optional.of(sampleBooking));

    ResponseEntity<Booking> responseEntity = bookingController.getTutorialById(1L);

    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  void getTutorialById_NotFoundTest() {
    when(managerRepository.findById(anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        ResourceNotFoundException.class,
        () -> {
          bookingController.getTutorialById(1L);
        });
  }
}
