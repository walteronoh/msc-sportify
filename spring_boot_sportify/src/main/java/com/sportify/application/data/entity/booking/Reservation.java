package com.sportify.application.data.entity.booking;

import java.util.ArrayList;
import java.util.List;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.converters.ListPairConverter;
import com.sportify.application.data.entity.venue.SeatingSection;
import com.sportify.application.data.utilities.Pair;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation extends AbstractEntity {

    @ManyToOne
    private SeatingSection section;
    @Convert(
        converter = ListPairConverter.class
    )
    @Column(insertable = false, updatable = false)
    private List <Pair <Integer, Integer>> seats = new ArrayList<>();
    private float cost;

    public Reservation (SeatingSection s, List <Pair <Integer, Integer>> seats) {
        this.section = s;
        this.seats.addAll(seats);
        // this.cost = s.getPrice() * seats.size();
        this.cost = 0;
    }
    public Reservation () {}

    public float getCost(){
        return this.cost;
    }
    
    public SeatingSection getSection() {
        return this.section;
    }

    public List <Pair <Integer, Integer>> getSeats() {
        return this.seats;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    public void setSection(SeatingSection section) {
        this.section = section;
    }
    public void setSeats(List <Pair <Integer, Integer>> seats_) {
        if (seats_ != null) {
            if(this.seats == null || this.seats.isEmpty()) {
                this.seats = seats_;
            }
        }
    }

}
