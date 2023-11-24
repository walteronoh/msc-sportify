package com.sportify.application.data.repository.venue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.entity.venue.VenueSection;

public interface VenueSectionRepository extends JpaRepository<VenueSection,Long>{
    @Query("select v from VenueSection v " +
            "where lower(v.name) like lower(concat('%', :searchTerm, '%')) ")
    List<VenueSection> search(@Param("searchTerm") String searchTerm);

    List<VenueSection> findAllByVenue(Venue venue);
}
