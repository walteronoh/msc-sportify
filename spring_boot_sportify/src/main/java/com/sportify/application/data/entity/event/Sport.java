package com.sportify.application.data.entity.event;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Sport extends AbstractEntity {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
