import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Seating {
   private Venue venue;
   private List <SeatingSection> sections;
   
   public Seating (Venue v) {
    this.venue = v;
    var sections = new ArrayList<SeatingSection> ();
    for (VenueSection vs : v.getSeatSections()) {
        var sec = new SeatingSection(vs);
        sections.add(sec);
    }
   }
   public Venue getVenue() {
        return this.venue;
   }

   public List <SeatingSection> getSeatingSections() {
        return this.sections;
   }

   public int getCapacity () {
    var cap = 0;
    for(SeatingSection s: this.sections) {
        cap += s.getCapacity();
    }
    return cap;
   }

   public Optional <Float> reserve(Reservation res) {
        var booked = new ArrayList<Pair<Integer,Integer>> ();
        
        var sec = res.getSection();
        var seats = res.getSeats();

        for (Pair<Integer,Integer> p : seats) {
            if (sec.checkAvailable(p.fst(), p.snd())) {
                sec.book(p.fst(), p.snd());
                booked.add(p);
            }
            else {
                booked.forEach((q -> {
                    sec.unBook(q.fst(), q.snd());
                }));
                return Optional.empty();
            }
        }
        return Optional.of(res.getCost());
    }

   public Optional <Float> unReserve(Reservation res) {
        var unBooked = new ArrayList<Pair<Integer,Integer>> ();
        
        var sec = res.getSection();
        var seats = res.getSeats();

        for (Pair<Integer,Integer> p : seats) {
            if (sec.unBook(p.fst(), p.snd())) {
                unBooked.add(p);
            }
            else {
                unBooked.forEach((q -> {
                    sec.book(q.fst(), q.snd());
                }));
                return Optional.empty();
            }
        }
        return Optional.of(res.getCost());
    }
}


