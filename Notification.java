import java.util.Date;

public class Notification extends CreateID {
    private String id;
    private Notifiable event;
    private String description;
    private Severity severity;
    private Date timestamp;

    public Notification (Notifiable n, 
                        String desc,
                        Severity severity) {
        this.id = createID();
        this.event = n;
        this.description = desc;
        this.severity = severity;
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

enum Severity {
    URGENT, PROMOTION, INFORMATION
}
