package com.sportify.application.data.entity.booking;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.annotations.CreationTimestamp;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.payment.Payment;
import com.sportify.application.data.entity.venue.VenueSection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

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
    // @NotEmpty
    // private Boolean madeReservations;
    @NotNull
    private double totalReserved = 1;
    @NotNull
    private double totalBill;
    @NotNull
    private double amountPaid = 1;
    @NotNull
    @ManyToOne
    VenueSection venueSection;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<Payment> payments;

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
