package com.sportify.application.data.entity.venue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.booking.Reservation;
import com.sportify.application.data.utilities.Pair;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class Seating extends AbstractEntity {

    @OneToOne
    private Venue venue;
    @OneToMany
    private Set<SeatingSection> sections = new HashSet<>();

    public Set<SeatingSection> getSeatingSections() {
        return new HashSet<>(this.sections);
    }

    public Venue getVenue() {
        return this.venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public void setSections(Set<SeatingSection> sections) {
        this.sections.clear();
        this.sections.addAll(sections);
    }

    public int getCapacity() {
        var cap = 0;
        for (SeatingSection s: this.sections) {
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

    public boolean cancelReservations(Set <Reservation> resvs) {
        var cancelledReses =  new HashSet<Reservation> (); // Successfully cancelled reservations
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

    public boolean makeReservations(Set <Reservation> resvs) {
        var madeReses = new HashSet<Reservation> ();
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
