import java.util.Date;
import java.util.List;

public class Game {
    private int id;
    private String title;
    private Date date;
    private Venue venue;
    private List<String> booked;
    private List<String> participant;
    private String outcome;
    private Enum<Sport> sport;
    private boolean played;

    public int getCapacity() {
    }

    public Date getDate() {
        return this.date;
    }

    public Venue getVenue() {
        return this.venue;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public boolean reserve(SeatSection seatSection, List<String> seats) {

    }

    public boolean unReserve(SeatSection seatSection, List<String> seats) {
    }

    public List<String> getAvailableSeats(SeatSection seatSection) {
    }

    public void makeNotification(String description, Enum<Severity> severity) {
    }
}

enum Sport {
    FOOTBALL, BASKETBALL, RUGBY, HANDBALL, CHESS, DARTS, TENNIS, BOXING, HOCKEY
}
