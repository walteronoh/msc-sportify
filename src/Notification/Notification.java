package Notification;

import java.util.Date;

import Enum.Severity;
import Utils.CreateID;
import Utils.Timestamped;

public class Notification implements Timestamped{
    private String id;
    private Notifiable event;
    private String description;
    private Severity severity;
    private Date timestamp;

    public Notification (Notifiable n, 
                        String desc,
                        Severity severity) {
        this.id = CreateID.createNamedID("NT");
        this.event = n;
        this.description = desc;
        this.severity = severity;
        this.timestamp = now();
    }

    public Notifiable getEvent() {
        return this.event;
    }

    public String getDescription() {
        return this.description;
    }

    public Severity severity() {
        return this.severity;
    }

    public String getId() {
        return this.id;
    }

    public Date getTime() {
        return this.timestamp;
    }
}

