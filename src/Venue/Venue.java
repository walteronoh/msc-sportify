package Venue;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Venue {
    private String location;
    private List<VenueSection> seatSections;
    private String description;

    public Venue (String loc, String desc) {
        this.location = loc;
        this.description = desc;
        this.seatSections = new LinkedList<VenueSection>();
    }
    public Venue (String loc, String desc, List<VenueSection> sections) {
        this.location = loc;
        this.description = desc;
        this.seatSections = sections;
    }
    
    public String getLocation() {
        return this.location;
    }
    public void setLocation (String loc) {
        this.location = loc;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }

    public List<VenueSection> getSeatSections() {
        return this.seatSections;
    }

    public Optional <VenueSection> getSeatSection(String id) {
        return this.seatSections
                         .stream()
                         .filter((s -> s.getId() == id))
                         .findFirst();
    }

    public boolean addSeatSection (VenueSection sec) {
        return this.seatSections.add(sec);
    } 
}
