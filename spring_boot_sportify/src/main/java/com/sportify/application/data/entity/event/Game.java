package com.sportify.application.data.entity.event;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.SeatingSection;
import com.sportify.application.data.entity.booking.Reservation;
import com.sportify.application.data.entity.notification.Notice;
import com.sportify.application.data.entity.notification.Notifiable;
import com.sportify.application.data.entity.participant.Participant;
import com.sportify.application.data.entity.venue.Seating;
import com.sportify.application.data.entity.venue.Venue;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game extends AbstractEntity implements Notifiable {
    @NotEmpty
    private String title = "New Game";
    @NotEmpty
    private String description = "Description";
    @NotEmpty
    private Date gameDate;
    @NotBlank
    private String outcome = "";
    @NotNull
    @ManyToOne
    private Sport sport;
    @NotBlank
    private boolean played = false;
    @OneToOne
    private Seating seating;
    @ElementCollection
    @CollectionTable(name = "GameParticipant")
    private Set <Participant> participants = new HashSet<>();
    @OneToMany
    private final Set <Notice> notices = new HashSet<>();

    public Game () {
        this.gameDate = new Date();
    }
    public Game (String title_,
                 Sport sport_,
                 String desc_,
                 Date date_
                 ) {
        this.title = title_;
        this.sport = sport_;
        this.description = desc_;
        this.gameDate = date_;
    }

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

    public @NotEmpty Boolean makeReservations(Set<Reservation> r) {
        return this.seating.makeReservations(r);
    }
    public boolean cancelReservations(Set<Reservation> reservations) {
        return this.seating.cancelReservations(reservations);
    }

    public Set<Notice> getNotices() {
        return new HashSet<>(this.notices);
    }
    public void setParticipants(Set<Participant> participants) {
        this.participants.clear();
        this.participants.addAll(participants);
    }
    public void addParticipant (Participant p) {
        this.participants.add(p);
    }
    public Set<Participant> getParticipants() {
        return new HashSet<>(participants);
    }
    public Seating getSeating() {
        return seating;
    }
    public void setSeating(Seating seating) {
        this.seating = seating;
    }
    public Set<SeatingSection> getSections() {
        return this.seating.getSeatingSections();
    }
    public Venue getVenue() {
        return this.seating.getVenue();
    }
    public int getCapacity() {
        return this.seating.getCapacity();
    }
}
