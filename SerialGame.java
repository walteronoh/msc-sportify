import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Think about making this a composition rather than inheritance
public class SerialGame extends Game {
    private List<Game> previous;    
    private List<Game> upcoming;
    private int seriesNo;

    public SerialGame ( String t,
                        Sport sp,
                        String desc,
                        List <String> particips,
                        Seating s,
                        List<Game> prev,
                        List<Game> nxt,
                        int seriesNo
                        ) {
        super(t, sp, desc, particips, s);
        this.previous = prev;
        this.upcoming = nxt;
    }

    public SerialGame ( String t,
                        Sport sp,
                        String desc,
                        List <String> particips,
                        Seating s,
                        int seriesNo
                        ) {
        this(t, sp, desc, particips, s, new ArrayList<Game>(), new ArrayList<Game>(), seriesNo);
    }

    public List<Game> getPrevious() {
        return this.previous;
    }
    public Optional <Game> getPreviousGame() {
        if (this.previous == null || this.previous.isEmpty()) {
            return Optional.empty();
        }
        else {
            var res = this.previous.get(this.previous.size()-1);
            return Optional.of(res);
        }
    }

    

    public List<Game> getUpcoming() {
        return this.upcoming;
    }
    public Optional<Game> getUpcomingGame() {
        if (this.upcoming == null || this.upcoming.isEmpty()) { // Get last element
            return Optional.empty();
        }
        else {
            var res = this.previous.get(0);
            return Optional.of(res);
        }
    }

    public int getSeriesNo() {
        return this.seriesNo;
    }
}
