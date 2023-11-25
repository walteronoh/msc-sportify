package com.sportify.application.data.entity.notification;

import java.sql.Timestamp;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.enums.Severity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Notice extends AbstractEntity {
    @NotEmpty
    private String sender;
    @NotEmpty
    private String description;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Severity severity;
    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="`TIME`", updatable = false,insertable = false)
    private Timestamp timestamp;

    public Notice () {}
    public Notice(Notifiable notifiable, String description2, Severity severity2) {
        this.sender = notifiable.getClass().descriptorString();
        this.description = description2;
        this.severity = severity2;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
    public Severity getSeverity() {
        return this.severity;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
}
