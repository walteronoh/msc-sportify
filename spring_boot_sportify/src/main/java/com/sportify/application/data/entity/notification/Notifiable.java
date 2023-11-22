package com.sportify.application.data.entity.notification;

import java.util.Set;

import com.sportify.application.data.entity.enums.Severity;

public interface Notifiable {
    default public void makeNotification(String description, Severity severity) {
        var note = new Notice(this, description, severity);
        this.getNotices().add(note);
    }
    public Set<Notice> getNotices();
}
