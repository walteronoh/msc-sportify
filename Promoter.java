import java.util.ArrayList;
import java.util.List;

public class Promoter extends GenericUser{
   List<Game> games;
   List<Series> series; 

   public Promoter(String name_, String email_, String pass) {
        super(name_, email_, pass);
        this.games = new ArrayList<Game>();
        this.series = new ArrayList<Series>();
   }

   public List<Game> getGames() {
        return this.games;
   }
   public List<Series> getSeries() {
        return this.series;
   }
   public void addGame(Game g) {
        this.games.add(g);
   }
   public void addSeries(Series s) {
        this.series.add(s);
   }
   public boolean addNotification(Game g, String desc, Severity sev) {
        if (this.games.contains(g)) {
                g.makeNotification(desc, sev);
                return true;
        }
        else {
                return false;
        }
   }
}
