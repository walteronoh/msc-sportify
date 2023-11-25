package com.sportify.application.data.entity.venue;

import com.sportify.application.data.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class VenueSection extends AbstractEntity {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private Double capacity;
    @NotEmpty
    private String sectionMode;
    @NotNull
    @ManyToOne
    private Venue venue;
    
    private Double seatPrice = 0.0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public void setSectionMode(String sectionMode) {
        this.sectionMode = sectionMode;
    }

    public String getSectionMode() {
        return sectionMode;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setSeatPrice(Double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Double getSeatPrice() {
        return seatPrice;
    }
}
