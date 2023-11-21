package com.sportify.application.data.entity.participant;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.event.Sport;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Participant extends AbstractEntity {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    @ManyToOne
    private Sport sport;

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

    public void setSport(Sport sport) {
        this.sport = sport;
        }
    
        public Sport getSport() {
        return this.sport;
        }
}
