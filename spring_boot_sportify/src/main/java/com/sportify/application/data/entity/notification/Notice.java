package com.sportify.application.data.entity.notification;

import java.sql.Date;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Notice extends AbstractEntity {
    @NotEmpty
    private String sender;
    @NotEmpty
    private String description;
    @NotEmpty
    private String severity;
    @NotEmpty
    private Date date;

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

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return this.severity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }
}
