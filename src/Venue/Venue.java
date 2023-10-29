package Venue;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import Tagging.Taggable;

public class Venue implements Taggable{
    private String name;
    private String location;
    private List<VenueSection> seatSections;
    private String description;

    public Venue (String name_, String loc, String desc) {
        this.name = name_;
        this.location = loc;
        this.description = desc;
        this.seatSections = new LinkedList<VenueSection>();
    }
    public Venue (String name_, String loc, String desc, List<VenueSection> sections) {
        this.name = name_;
        this.location = loc;
        this.description = desc;
        this.seatSections = sections;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name_) {
        this.name = name_;
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
