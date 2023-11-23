package com.sportify.application.data.repository.venue;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.venue.Seating;

public interface SeatingRepository extends JpaRepository<Seating, Long>{
    
}
