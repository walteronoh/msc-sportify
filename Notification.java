import java.util.Date;

public class Notification {
    private int id;
    private Game game;
    private String description;
    private Severity severity;
    private Date timestamp;

    public Game getGame() {
    }

    public String getDescription() {
    }

    public Severity severity() {
    }

    public int getId() {
    }

    public Date getTime() {
    }
}

enum Severity {
    URGENT, PROMOTION, INFORMATION
}
