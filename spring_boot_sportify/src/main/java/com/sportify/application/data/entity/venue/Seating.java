package com.sportify.application.data.entity.venue;

import java.util.Set;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.SeatingSection;
import com.sportify.application.data.entity.booking.Reservation;

import jakarta.validation.constraints.NotEmpty;

public class Seating extends AbstractEntity {

    public Set<SeatingSection> getSeatingSections() {
        return null;
    }

    public Venue getVenue() {
        return null;
    }

    public int getCapacity() {
        return 0;
    }

    public @NotEmpty Boolean makeReservations(Set<Reservation> r) {
        return null;
    }

    public boolean cancelReservations(Set<Reservation> reservations) {
        return false;
    }
    
}
