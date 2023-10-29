package Notification;

import java.util.List;

import Enum.Severity;

public interface Notifiable {
    default public void makeNotification(String description, Severity severity) {
        var note = new Notification(this, description, severity);
        this.getNotices().add(note);
    }
    public List<Notification> getNotices();
}
