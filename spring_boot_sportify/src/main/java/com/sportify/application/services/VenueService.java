package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.repository.venue.VenueRepository;

@Service
public class VenueService {
    public final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public void saveVenue(Venue venue) {
        if (venue == null) {
            System.err.println("Venue is null.");
            return;
        }
        venueRepository.save(venue);
    }

    public void deleteVenue(Venue venue) {
        venueRepository.delete(venue);
    }

    public List<Venue> findAllVenues() {
        return venueRepository.findAll();
    }

    public List<Venue> findVenue(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return venueRepository.findAll();
        } else {
            return venueRepository.search(filterText);
        }
    }
}


