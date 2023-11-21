package com.sportify.application.data.entity.event;

import java.util.Date;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game extends AbstractEntity {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private Date gameDate;
    @NotBlank
    private String outcome;
    @NotNull
    @ManyToOne
    private Sport sport;
    @NotBlank
    private boolean played;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Date getGameDate() {
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

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public boolean getPlayed() {
        return this.played;
    }
}
