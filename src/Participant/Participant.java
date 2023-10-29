package Participant;
import java.util.ArrayList;
import java.util.List;

import Notifiable;
import Notification;
import Enum.Sport;
import Utils.CreateID;

public abstract class Participant implements Notifiable {
    protected String name;
    protected String description;
    protected String id;
    protected Sport sport;
    protected List<String> nickNames;    
    protected List<Notification> notices;

    public Participant(String name_, 
                       String desc, 
                       Sport sp, 
                       List<String> nick, 
                       List<Notification> notices_
                       ) {
        this.id = CreateID.createNamedID("PAR");
        this.name = name_;
        this.description = desc;
        this.sport = sp;
        this.nickNames = nick;
        this.notices = notices_;
    }
    public Participant(String name_, String desc, Sport sp) {
        this(name_,desc, sp, 
        new ArrayList<String>(), 
        new ArrayList<Notification>()
        );
    }

    public String getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String n) {
        this.name = n;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }

    public Sport getSport() {
        return this.sport;
    }
    public void setSport(Sport s) {
        this.sport = s;
    }

    public List<String> getNickNames() {
        return this.nickNames;
    }
    public void setNickName(List<String> ns) {
        this.nickNames = ns;
    }
    public void addNickName( String n) {
        this.nickNames.add(n.toLowerCase());
    }
    public boolean isNickName (String n) {
        return this.nickNames.contains(n.toLowerCase());
    }

    public List<Notification> getNotices() {
        return notices;
    }
}
