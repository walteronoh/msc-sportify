package com.sportify.application.data.entity.participant;

import java.util.HashSet;
import java.util.Set;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.enums.Severity;
import com.sportify.application.data.entity.event.Sport;
import com.sportify.application.data.entity.notification.Notice;
import com.sportify.application.data.entity.notification.Notifiable;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Participant extends AbstractEntity implements Notifiable{
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    @ManyToOne
    private Sport sport;
    @OneToMany
    protected Set<Notice> notices = new HashSet<>();
    @ElementCollection
    @CollectionTable (
        name = "NICKNAME",
        joinColumns = @JoinColumn(name = "PART_ID")
    )
    @Column(name = "NAME")
    protected Set<String> nickNames = new HashSet<>();

    public Participant() {}
    public Participant (String name_,
                        String desc_,
                        Sport sport_
                        ) {
        this.name = name_;
        this.description = desc_;
        this.sport = sport_;
    }
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

    public Set<String> getNickNames() {
        return new HashSet<>(this.nickNames);
    }
    public void setNickNames(Set<String> nickNames) {
        this.nickNames.clear();
        this.nickNames.addAll(nickNames);
    }
    @Override
    public Set<Notice> getNotices() {
        return new HashSet<>(this.notices);
    }
    public void addNotice(Notice n) {
        this.notices.add(n);
    }

    @Override
    public void makeNotification(String description, Severity severity) {
        this.notices.add(new Notice(this, description, severity));
    }
}
