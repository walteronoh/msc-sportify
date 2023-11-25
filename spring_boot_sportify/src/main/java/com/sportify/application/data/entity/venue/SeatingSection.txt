package com.sportify.application.data.entity.venue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.enums.BookingStatus;
import com.sportify.application.data.utilities.Pair;
import com.vladmihalcea.hibernate.type.array.EnumArrayType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class SeatingSection extends AbstractEntity {

    @Type(value = EnumArrayType.class,
            parameters = @Parameter(
                    name = "sql_array_type",
                    value = "seat_status"
            )
    )
    @Column(
            name = "seat",
            columnDefinition = "seat_status[][]"
    )
    private BookingStatus[][] seat;
    private int capacity;
    private int bookedCapacity;
    private float price;
    @OneToOne
    private VenueSection section;

    public SeatingSection () {}
    public SeatingSection (VenueSection sec) {
        this(sec, sec.getSeatPrice());
    }
    public SeatingSection (VenueSection sec, float price) {
        this.setSection(sec);
        this.capacity = sec.getCapacity();
        this.bookedCapacity = 0;
        this.price = price;

    }
    public int getCapacity() {
        return this.capacity;
    }

    public int getBookedCapacity() {
        return this.bookedCapacity;
    }

    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public VenueSection getSection() {
        return section;
    }
    public void setSection(VenueSection section) {
        this.section = section;
        List <List<BookingStatus>> rowList = new ArrayList<>();
        this.section.getRows().forEach(i ->
                        {
                            var l = new ArrayList<BookingStatus>();
                            for (int j = 0; j < i; j++) {
                               l.add(BookingStatus.Available);
                            }
                            rowList.add(l);
                        });
        this.seat = rowList.stream()
                            .map(e -> e.toArray(BookingStatus[]::new))
                            .toArray(BookingStatus[][]::new);
    }

    public boolean book(Integer row, Integer col) {
       if(!checkAvailable(row, col)) {
          return false;
       }
       else {
          this.seat[row][col] = BookingStatus.Reserved;
          this.bookedCapacity++;
          return true;
       }
    }
    public boolean unBook(Integer row, Integer col) {
       if(checkAvailable(row, col)) {
            return false;
       }
       else {
            this.seat[row][col] = BookingStatus.Available;
            this.bookedCapacity--;
            return true;
       }
    }

    public Boolean checkAvailable(Integer row, Integer col) {
        return (BookingStatus.Available == this.seat[row][col]);
    }
    public List <Pair <Integer, Integer>> getAvailableSeats () {
        var available = new ArrayList<Pair<Integer, Integer>> ();
        int row = 0;
        int col = 0;
    
        for (BookingStatus[] l : this.seat) {
            for (BookingStatus s: l) {
                if (s == BookingStatus.Available) {
                    available.add(new Pair<>(row, col));
                    col++;
                }
            }
            row++;
        }
        return available;
    }
    public BookingStatus[][] getSeats() {
        return seat;
    }

}
