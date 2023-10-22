import java.util.List;

public class Reservation {
    private SeatingSection section;
    private List <Pair <Integer, Integer> > seats ;
    private float cost;

    public Reservation (SeatingSection s, List <Pair <Integer, Integer>> seats) {
        this.section = s;
        this.seats = seats;
        this.cost = s.getPrice() * seats.size();
    }

    public float getCost(){
        return this.cost;
    }
    
    public SeatingSection getSection() {
        return this.section;
    }

    public List <Pair <Integer, Integer>> getSeats() {
        return this.seats;
    }    
}
