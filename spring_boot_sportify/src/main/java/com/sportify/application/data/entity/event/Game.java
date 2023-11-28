package com.sportify.application.data.entity.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.participant.Participant;
import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.entity.venue.VenueSection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game extends AbstractEntity {
    @NotEmpty
    private String title = "New Game";
    @NotEmpty
    private String description = "Description";
    // @NotBlank
    private String outcome = "";
    @NotNull
    private LocalDate gameDate;
    @NotNull
    @ManyToOne
    private Sport sport;
    @NotNull
    @ManyToOne
    private Venue venue;
    // @NotBlank
    private boolean played = false;
    @OneToMany
    private Set<Participant> participants = new HashSet<>();

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }
    public Set<Participant> getParticipants() {
        return participants;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public LocalDate getGameDate() {
        return this.gameDate;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return this.outcome;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Sport getSport() {
        return this.sport;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Venue getVenue() {
        return this.venue;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public boolean getPlayed() {
        return this.played;
    }

    public List<VenueSection> getVenueSections() {
        if (venue != null) {
            return venue.getVenueSections();
        }
        return new ArrayList<VenueSection>();
    }

    public double getTotalSeats() {
        AtomicReference<Double> totalSeats = new AtomicReference<>(0.0);
        this.venue.getVenueSections().forEach(section -> {
            totalSeats.getAndUpdate(seat -> seat + section.getCapacity());
        });
        return totalSeats.get();
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public double getBookedSeats() {
        AtomicReference<Double> bookedSeats = new AtomicReference<>(0.0);
        this.bookings.forEach(booking -> {
            bookedSeats.getAndUpdate(seat -> seat + booking.getTotalReserved());
        });
        return bookedSeats.get();
    }

    public double calculateRemainingSeats() {
        return this.getTotalSeats() - this.getBookedSeats();
    }
}
