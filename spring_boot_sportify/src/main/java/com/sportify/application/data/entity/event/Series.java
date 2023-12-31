package com.sportify.application.data.entity.event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.enums.Severity;
import com.sportify.application.data.entity.notification.Notice;
import com.sportify.application.data.entity.notification.Notifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Series extends AbstractEntity implements Notifiable {
    
    @OneToMany
    private List<Game> games = new ArrayList<>();
    @OneToMany
    private Set <Notice> notices = new HashSet<>();
    private String title;
    private String description;

    public Series () {}
    public Series (String title_,
                String desc) {
        this.title = title_;
        this.description = desc;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void makeNotification(String description, Severity severity) {
        this.addNotice(new Notice(this, description, severity));
    }
    @Override
    public Set<Notice> getNotices() {
        return new HashSet<>(this.notices);
    }
    @Override
    public void addNotice(Notice notice) {
        this.notices.add(notice);
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    public void addGame(Game g) {
        this.games.add(g);
    }

    protected String seriesIdentifier(Integer n) {
        return "Game Week ".concat(n.toString());
    }
    public Optional<String> getGameIdentifier(Game g) {
        int index = this.games.indexOf(g);
        if (index == -1) {
            return Optional.empty();
        }
        else {
            return Optional.of( this.seriesIdentifier(index + 1));
        }
    }

    public Optional<Game> getNextGame (Game g) {
        var index = this.games.indexOf(g);
        if (index == -1) { //Game g not in series
            return Optional.empty();
        }
        else if (this.games.size() == (index + 1)) { //Game g is last game
            return Optional.empty();
        }
        else {
            return Optional.of(this.games.get(index + 1));
        }
    }
    public Optional<Game> getPrevGame (Game g) {
        var index = this.games.indexOf(g);
        if (index == -1) { //Game g not in series
            return Optional.empty();
        }
        else if (index == 0) { //Game g is first game
            return Optional.empty();
        }
        else {
            return Optional.of(this.games.get(index - 1));
        }
    }
    
}
