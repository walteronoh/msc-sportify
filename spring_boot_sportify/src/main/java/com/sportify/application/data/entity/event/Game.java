package com.sportify.application.data.entity.event;

import java.util.Date;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Game extends AbstractEntity {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Date gameDate;
    @NotBlank    
    private String outcome;
    @NotBlank
    private String sport;
    @NotBlank
    private boolean played;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
