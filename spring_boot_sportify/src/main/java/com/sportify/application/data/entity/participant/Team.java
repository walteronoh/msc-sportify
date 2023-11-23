package com.sportify.application.data.entity.participant;

import java.util.HashSet;
import java.util.Set;

import com.sportify.application.data.entity.event.Sport;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Team extends Participant{
    
    @ElementCollection
    @CollectionTable(name = "TeamPlayers")
    private final Set<PositionalPlayer> players = new HashSet<>();
    @OneToOne
    private Manager manager;
    private String city;
    private String stadium;

    public Team() {
        super();
    }
    public Team(String name_,
                String desc,
                Sport sp,
                Manager man,
                Set<PositionalPlayer> plyrs,
                String city_,
                String stadium_
                ) {
       super(name_, desc, sp);
       this.manager = man;
       this.players.addAll(plyrs);
       this.city = city_;
       this.stadium = stadium_;
    }

    public String getCity() {
        return this.city;
    }
    public void setCity(String c) {
        this.city = c;
    }

    public String getStadium () {
        return this.stadium;
    }
    public void setStadium(String s) {
        this.stadium = s;
    }

    public Manager getManager() {
        return this.manager;
    }
    public void setManager(Manager m) {
        this.manager = m;
    }
    
    public Set<PositionalPlayer> getPlayers() {
        return new HashSet<>(this.players);
    }
    public void setPlayers(Set<PositionalPlayer> plyrs) {
        this.players.clear();
        this.players.addAll(plyrs);
    }
    public void addPlayer(PositionalPlayer p) {
        this.players.add(p);
    }
    public void removePlayer(PositionalPlayer p) {
        this.players.remove(p);
    }
}
