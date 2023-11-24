package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.entity.venue.VenueSection;
import com.sportify.application.data.repository.venue.VenueRepository;
import com.sportify.application.data.repository.venue.VenueSectionRepository;

@Service
public class VenueService {
    public final VenueRepository venueRepository;
    public final VenueSectionRepository venueSectionRepository;

    public VenueService(VenueRepository venueRepository, VenueSectionRepository venueSectionRepository) {
        this.venueRepository = venueRepository;
        this.venueSectionRepository = venueSectionRepository;
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

    public Venue findvenueById(Long venueId) {
        return venueRepository.findById(venueId).orElse(null);
    }

    // Venue Section methods
    public void saveVenueSection(VenueSection venueSection) {
        if (venueSection == null) {
            System.err.println("Venue Section is null.");
            return;
        }
        venueSectionRepository.save(venueSection);
    }

    public void deleteVenueSection(VenueSection venueSection) {
        venueSectionRepository.delete(venueSection);
    }

    public List<VenueSection> findAllVenueSections() {
        return venueSectionRepository.findAll();
    }

    public List<VenueSection> findVenueSection(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return venueSectionRepository.findAll();
        } else {
            return venueSectionRepository.search(filterText);
        }
    }

    public List<VenueSection> findVenueSections(Venue venue) {
        return venueSectionRepository.findAllByVenue(venue);
    }
}
