import java.util.ArrayList;
import java.util.List;

public class Team extends Participant{
    private List<Player> players; 
    private Manager manager;
    private String city;
    private String stadium;

    public Team(String name_,
                String desc,
                Sport sp,
                List <String> nick,
                Manager man,
                List<Player> plyrs,
                String city_,
                String stadium_
                ) {
       super(name_, desc, sp, nick);
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
       this.players = new ArrayList<Player>();
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
    
    public List<Player> getPlayers() {
        return this.players;
    }
    public void setPlayers(List<Player> plyrs) {
        this.players = plyrs;
    }
    public void addPlayer(Player p) {
        this.players.add(p);
    }
    public void removePlayer(Player p) {
        this.players.remove(p);
    }
}
