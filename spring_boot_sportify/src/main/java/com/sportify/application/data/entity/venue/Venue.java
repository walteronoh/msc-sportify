package com.sportify.application.data.entity.venue;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Venue extends AbstractEntity {
    @NotNull
    private String name;
    @NotNull
    private String description;

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
}
