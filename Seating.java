import java.util.ArrayList;
import java.util.List;

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

   public int getBookedCapacity () {
    var cap = 0;
    for(SeatingSection s: this.sections) {
        cap += s.getBookedCapacity();
    }
    return cap;
   }

    public boolean cancelReservations(List<Reservation> resvs) {
        var cancelledReses =  new ArrayList<Reservation> (); // Successfully cancelled reservations
        for (Reservation res : resvs) {
            var result = this.unReserve(res);
            if (result) {
                cancelledReses.add(res);
            } 
            else {
                for(Reservation cres : cancelledReses) {
                    this.reserve(cres);
                }
                return false;
            }
        }
        return true;
    }

    public boolean makeReservations(List<Reservation> resvs) {
        var madeReses = new ArrayList<Reservation> ();
        for(Reservation res: resvs) {
            var success = this.reserve(res);
            if (success) {
                    madeReses.add(res);
            }
            else {
                for (Reservation mres: madeReses) {
                    this.unReserve(mres);
                }
                return false;
            }
        }
        return true;
    }

   public boolean reserve(Reservation res) {
        var booked = new ArrayList<Pair<Integer,Integer>> ();
        
        var sec = res.getSection();
        var seats = res.getSeats();

        for (Pair<Integer,Integer> p : seats) {
            if (sec.book(p.fst(), p.snd())) {
                booked.add(p);
            }
            else {
                booked.forEach((q -> {
                    sec.unBook(q.fst(), q.snd());
                }));
                return false;
            }
        }
        return true;
    }

   public boolean unReserve(Reservation res) {
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
                return false;
            }
        }
        return true;
    }
}


