package Participant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Enum.Sport;
import Notification.Notification;

public class Team extends Participant implements GameParticipant{
    private Set<PositionalPlayer> players; 
    private Manager manager;
    private String city;
    private String stadium;

    public Team(String name_,
                String desc,
                Sport sp,
                List <String> nick,
                Manager man,
                Set<PositionalPlayer> plyrs,
                String city_,
                String stadium_,
                List <Notification> notes_
                ) {
       super(name_, desc, sp, nick, notes_);
       this.manager = man;
       this.players = plyrs;
       this.city = city_;
       this.stadium = stadium_;
    }
    public Team(String name_,
                String desc,
                Sport sp,
                Manager man,
                String city_,
                String stadium_
                ) {
       super(name_, desc, sp);
       this.manager = man;
       this.players = new HashSet<PositionalPlayer>();
       this.city = city_;
       this.stadium = stadium_;
    }

    public String getCity() {
        return this.city;
    }
    public void setCity(String c) {
        this.city = c;
    }

    public String getStadium () {
        return this.stadium;
    }
    public void setStadium(String s) {
        this.stadium = s;
    }

    public Manager getManager() {
        return this.manager;
    }
    public void setManager(Manager m) {
        this.manager = m;
    }
    
    public Set<PositionalPlayer> getPlayers() {
        return this.players;
    }
    public void setPlayers(Set<PositionalPlayer> plyrs) {
        this.players = plyrs;
    }
    public void addPlayer(PositionalPlayer p) {
        this.players.add(p);
    }
    public void removePlayer(PositionalPlayer p) {
        this.players.remove(p);
    }
}
