package com.sportify.application.data.entity.booking;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.annotations.CreationTimestamp;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.enums.PaymentMethod;
import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.payment.Payment;
import com.sportify.application.data.entity.venue.VenueSection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Booking extends AbstractEntity {
    // @NotEmpty
    // private Boolean attended = false;
    // @ManyToOne
    // private BUser user;
    @ManyToOne
    private Game game;
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    @CreationTimestamp
    private Date bookingDate;
    // @NotEmpty
    // private Boolean cancelled = false;

    private Boolean paidInFull = false;
    @OneToMany
    private Set<Payment> payments = new HashSet<>();
    @NotEmpty
    private Boolean madeReservations;
    @NotEmpty
    private Float totalBill;

    public Booking (BUser u,
                    Game g,
                    Set<Reservation> r
                    ) {
        this.user = u;
        this.game = g;
        this.reservations = r;
        this.madeReservations = g.makeReservations(r);
        float sum = 0;
        for (Reservation rv: r) {
            sum += rv.getCost();
        }
        this.totalBill = sum;
    }
    public Booking() {}

    public void makePayment(Float amount,
                            PaymentMethod method,
                            String receipt ) {
        var pmt = new Payment(this.user, this, method, receipt, amount);
        this.payments.add(pmt);

        float totalPaid = 0;
        for (Payment p: payments) {
            totalPaid += p.getAmount();
        }
        if (totalPaid >= totalBill) {
            this.paidInFull = true;
        }
    }
    public void setGame(Game game) {
        if (game != null) {
            this.game = game;
        }
    }
    public void setReservations(Set<Reservation> reservations_) {
        if (reservations_ != null) {
            if(this.reservations == null || this.reservations.isEmpty()) {
                this.reservations = reservations_;
                this.madeReservations = this.game.makeReservations(reservations);
            }
            else {
                madeReservations = !game.cancelReservations(this.reservations);
                madeReservations = game.makeReservations(reservations_);
            }
        }
    }
    public boolean reserve() {
        if(!this.madeReservations) {
            this.madeReservations = this.game.makeReservations(reservations);
        }
        return this.madeReservations;
    }
    public BUser getUser() {
        return user;
    }
    public void setUser(BUser user) {
        if (user != null) {
            this.user = user;
        }
    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // public Date getBookingDate() {
    // return bookingDate;
    // }

    // public void setAttended(Boolean attended) {
    // this.attended = attended;
    // }

    // public Boolean getAttended() {
    // return this.attended;
    // }

    // public void attend() {
    // setAttended(true);
    // }

    // public void setCancelled(Boolean cancelled) {
    // this.cancelled = cancelled;
    // }

    // public Boolean getCancelled() {
    // return this.cancelled;
    // }

    // public boolean isCancelled() {
    // return this.cancelled;
    // }

    // public boolean isPaidInFull() {
    // return paidInFull;
    // }

    public Boolean getPaidInFull() {
        return this.paidInFull;
    }

    public void setPaidInFull(Boolean paidInFull) {
        this.paidInFull = paidInFull;
    }

    public double getTotalReserved() {
        return this.totalReserved;
    }

    public void setTotalReserved(double totalReserved) {
        this.totalReserved = totalReserved;
    }

    public double getTotalBill() {
        return this.totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public VenueSection getVenueSection() {
        return venueSection;
    }

    public void setVenueSection(VenueSection venueSection) {
        this.venueSection = venueSection;
    }

    public double getBalance() {
        return this.totalBill - this.calculateTotalPayments();
    }

    public boolean isPaidInFull() {
        return getBalance() <= 0;
    }

    public List<Payment> getBookingPayments() {
        return this.payments;
    }

    public double calculateTotalPayments() {
        AtomicReference<Double> totalPayment = new AtomicReference<>(0.0);
        this.payments.forEach(payment -> {
            totalPayment.getAndUpdate(amount -> amount + payment.getAmount());
        });
        return totalPayment.get();
    }
}
