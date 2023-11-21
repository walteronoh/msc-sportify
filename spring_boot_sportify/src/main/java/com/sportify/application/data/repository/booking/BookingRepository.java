package com.sportify.application.data.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.booking.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    
}
