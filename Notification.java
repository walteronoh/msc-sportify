import java.util.Date;

public class Notification extends CreateID {
    private String id;
    private Game game;
    private String description;
    private Severity severity;
    private Date timestamp;

    public Notification (Game g, 
                        String desc,
                        Severity severity) {
        this.id = createID();
        this.game = g;
        this.description = desc;
        this.severity = severity;
    }

    public Game getGame() {
        return this.game;
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
