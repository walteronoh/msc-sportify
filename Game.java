import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Game extends CreateID implements Timestamped{
    private String id;
    private String title;
    private String description;
    private Date date;
    private Seating seating;
    private List<String> participants;
    private String outcome;
    private Sport sport;
    private boolean played;

    public Game (String t,
                 Sport sp,
                 String desc,
                 List <String> particips,
                 Seating s
                 ) {
        this.id = createID();
        this.date = today();
        this.title = t;
        this.description = desc;
        this.seating = s;
        this.participants =particips;
        this.sport = sp;
        this.outcome = "";
        this.played = false;
        
    }
    public Game (String t,
                 Sport sp,
                 String desc,
                 Seating s
                 ) {
        this(t, sp, desc, new ArrayList<String> (), s);
    }
    
    public void setParticipants (List<String> ps) {
        this.participants = ps;
    }
    public void addParticipants (String p) {
        this.participants.add(p);
    }
    public List<String> getParticipants () {
        return this.participants;
    }

    public int getCapacity() {
        return this.seating.getCapacity();
    }

    public Date getDate() {
        return this.date;
    }

    public Venue getVenue() {
        return this.seating.getVenue();
    }
    public String getTitle() {
        return this.title;
    }
    public String getID() {
        return this.id;
    }
    public void setTitle(String t) {
        this.title = t;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String t) {
        this.description = t;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public void setSeating (Seating s) {
        this.seating = s;
    }
    public Seating getSeating () {
        return this.seating;
    }

    public Optional <Float> reserve( Reservation res) {
        return this.seating.reserve(res);
    }

    public Optional <Float> unReserve(Reservation res) {
        return this.seating.unReserve(res);
    }

    public List<SeatingSection> getSections() {
        return this.seating.getSeatingSections();
    }

    public void makeNotification(String description, Enum<Severity> severity) {
    }
}

enum Sport {
    FOOTBALL, BASKETBALL, RUGBY, HANDBALL, CHESS, DARTS, TENNIS, BOXING, HOCKEY
}
