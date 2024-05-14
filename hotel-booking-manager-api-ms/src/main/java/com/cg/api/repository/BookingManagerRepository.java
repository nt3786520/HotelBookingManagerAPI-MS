package com.cg.api.repository;

import com.cg.api.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingManagerRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByDate(LocalDate date);
    List<Booking> findByGuestNameContainingIgnoreCase(String guestName);

}
