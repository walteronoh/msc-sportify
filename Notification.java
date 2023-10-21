import java.util.Date;

public class Notification extends CreateID {
    private int id;
    private Game game;
    private String description;
    private Severity severity;
    private Date timestamp;

    public Game getGame() {
        return this.game;
    }

    public String getDescription() {
        return this.description;
    }

    public Severity severity() {
        return this.severity;
    }

    public int getId() {
        return this.id;
    }

    public Date getTime() {
        return this.timestamp;
    }
}

enum Severity {
    URGENT, PROMOTION, INFORMATION
}
