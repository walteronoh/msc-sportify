package com.sportify.application.data.entity.User;

import java.util.HashSet;
import java.util.Set;

import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.tags.Taggable;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class BUser extends GenericUser {
    @OneToMany
    private Set<Booking> bookings = new HashSet<>();
    @Transient
    private Set<Taggable> tags = new HashSet<>();


    public BUser () {
        super();
    }
    public BUser(String name_,
                String email_,
                String pass_
                ) {
        super(name_, email_, pass_);
    }

    public Set<Booking> getBookings() {
        return new HashSet<>(this.bookings);
    }
    public void addBooking(Booking b) { // Investigate if this should fail
        this.getBookings().add(b);
    }
    public void setBookings(Set<Booking> bookings) {
        this.bookings.addAll(bookings);
    }

    public Set<Taggable> getTags() {
        return this.tags;
    }
    public void addTag(Taggable t) {
        this.tags.add(t);
    }
    public void setTags(Set<Taggable> tags_) {
        this.tags = tags_;
    }
}
