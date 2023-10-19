import java.util.List;

public class SerialGame extends Game {
    private List<Game> previous;    
    private List<Game> upcoming;
    private int seriesNo;

    public List<Game> getPrevious() {
        return this.previous;
    }

    public List<Game> getUpcoming() {
        return this.upcoming;
    }

    public int getSeriesNo() {
        return this.seriesNo;
    }
}
