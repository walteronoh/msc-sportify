package com.sportify.application.data.entity.event;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.participant.Participant;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class GameParticipant extends AbstractEntity{
   @NotNull
   @ManyToOne
   private Game game;
   @NotNull
   @ManyToOne
   private Participant participant;
   
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Participant getParticipant() {
        return participant;
    }
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
