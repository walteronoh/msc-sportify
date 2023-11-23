package com.sportify.application.data.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.booking.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    
}
