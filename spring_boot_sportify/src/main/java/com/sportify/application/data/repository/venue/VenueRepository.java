package com.sportify.application.data.repository.venue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.application.data.entity.venue.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    @Query("select v from Venue v " +
            "where lower(v.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Venue> search(@Param("searchTerm") String searchTerm);
}
