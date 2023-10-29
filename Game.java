import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Utils.CreateID;
import Utils.Timestamped;

public class Game implements Timestamped, Notifiable{
    private String id;
    private String title;
    private String description;
    private Date date;
    private Seating seating;
    private List <GameParticipant> participants;
    private List<Notification> notices;
    private String outcome;
    private Sport sport;
    private boolean played;

    public Game (String t,
                 Sport sp,
                 String desc,
                 List <GameParticipant> particips,
                 List <Notification> notices_,
                 Seating s
                 ) {
        this.id = CreateID.createNamedID("GM");
        this.date = today();
        this.title = t;
        this.description = desc;
        this.seating = s;
        this.participants = particips;
        this.notices = notices_;
        this.sport = sp;
        this.outcome = "";
        this.played = false;
        
    }
    public Game (String t,
                 Sport sp,
                 String desc,
                 Seating s
                 ) {
        this(t, sp, desc, 
            new ArrayList<GameParticipant> (), 
            new ArrayList<Notification>(), s);
    }
    
    public String getID() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public String getTitle() {
        return this.title;
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

    public String getOutcome () {
        return this.outcome;
    }
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public void setPlayed(boolean p) {
        this.played = p;
    }
    public boolean getPlayed() {
        return this.played;
    }

    public Sport geSport() {
        return this.sport;
    }
    public void setSport(Sport sp) {
        this.sport = sp;
    }

    public void setParticipants (List<GameParticipant> ps) {
        this.participants = ps;
    }
    public void addParticipants (GameParticipant p) {
        this.participants.add(p);
    }
    public List<GameParticipant> getParticipants () {
        return this.participants;
    }

    public List<Notification> getNotices () {
        return this.notices;
    }
    
    public void setSeating (Seating s) {
        this.seating = s;
    }
    public Seating getSeating () {
        return this.seating;
    }
    public List<SeatingSection> getSections() {
        return this.seating.getSeatingSections();
    }

    public Venue getVenue() {
        return this.seating.getVenue();
    }

    public int getCapacity() {
        return this.seating.getCapacity();
    }
    
    public boolean makeReservations (List<Reservation> resvs) {
        return this.seating.makeReservations(resvs);
    }
    public boolean cancelReservations(List<Reservation> resvs) {
        return this.seating.cancelReservations(resvs);
    }
}
