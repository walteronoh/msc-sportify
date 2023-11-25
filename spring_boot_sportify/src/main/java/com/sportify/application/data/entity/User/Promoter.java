package com.sportify.application.data.entity.User;

import java.util.HashSet;
import java.util.Set;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.Series;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Promoter extends GenericUser{
    @OneToMany
    Set< Game> games = new HashSet<>();
    @OneToMany
    Set<Series> series = new HashSet<>();
    
    public Promoter () {
        super();
    }
    public Promoter(String name_,
            String email_,
            String pass) {
        super(name_, email_, pass);
    }
    public Set<Game> getGames() {
        return new HashSet<>(games);
    }
    public Set<Series> getSeries() {
        return new HashSet<>(series);
    }

    public void addGame(Game g) {
        this.games.add(g);
    }
    public void addSeries(Series s) {
        this.series.add(s);
    }
    public void setGames(Set<Game> games) {
        this.games = games;
    }
    public void setSeries(Set<Series> series) {
        this.series = series;
    }
    
}
