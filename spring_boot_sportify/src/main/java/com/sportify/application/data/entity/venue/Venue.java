package com.sportify.application.data.entity.venue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Venue extends AbstractEntity {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private Double capacity;
    @NotEmpty
    private String location;
    @OneToMany
    private Set<VenueSection> seatSections = new HashSet<>();

    @OneToMany(mappedBy = "venue", fetch = FetchType.EAGER)
    private List<VenueSection> venueSections;

    public Venue() {
    }

    public Venue(String name_,
            String loc,
            String desc) {
        this.name = name_;
        this.location = loc;
        this.description = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getCapacity() {
        return this.capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSeatSections(Set<VenueSection> seatSections) {
        this.seatSections.clear();
        this.seatSections.addAll(seatSections);
    }

    public Set<VenueSection> getSeatSections() {
        return new HashSet<>(seatSections);
    }

    public boolean addSeatSection(VenueSection sec) {
        return this.seatSections.add(sec);
    }

    public List<VenueSection> getVenueSections() {
        return this.venueSections;
    }
}
