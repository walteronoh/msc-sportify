package com.sportify.application.data.repository.venue;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.venue.VenueSection;

public interface VenueSectionRepository extends JpaRepository<VenueSection,Long>{
}
