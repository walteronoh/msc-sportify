package com.sportify.application.data.entity.booking;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.enums.PaymentMethod;
import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.notification.Notice;
import com.sportify.application.data.entity.payment.Payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Booking extends AbstractEntity {
    @NotEmpty
    private Boolean attended;
    @ManyToOne
    private BUser user;
    @ManyToOne
    private Game game;
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    @CreationTimestamp
    private Date bookingDate;
    @NotEmpty
    private Boolean cancelled = false;
    @OneToMany
    private Set<Reservation> reservations = new HashSet<>();
    @OneToMany
    private Set<Notice> viewedNotices = new HashSet<>();
    @NotEmpty
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
    public boolean reserve() {
        if(!this.madeReservations) {
            this.madeReservations = this.game.makeReservations(reservations);
        }
        return this.madeReservations;
    }
    public BUser getUser() {
        return user;
    }
    public Game getGame() {
        return game;
    }
    public Date getBookingDate() {
        return bookingDate;
    }
    public Set<Payment> getPayments() {
        return new HashSet<>(payments);
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Boolean getAttended() {
        return this.attended;
    }
    public void attend() {
        setAttended(true);
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getCancelled() {
        return this.cancelled;
    }

    public void setPaidInFull(Boolean paidInFull) {
        this.paidInFull = paidInFull;
    }
    public boolean isCancelled() {
        return this.cancelled;
    }
    public boolean cancel() {
        return this.game.cancelReservations(this.reservations);
    }
    public boolean isPaidInFull () {
        return paidInFull;
    }
    public Boolean getPaidInFull() {
        return this.paidInFull;
    }

    public void setMadeReservations(Boolean madeReservations) {
        this.madeReservations = madeReservations;
    }

    public Boolean getMadeReservations() {
        return this.madeReservations;
    }

    public void setTotalBill(Float totalBill) {
        this.totalBill = totalBill;
    }

    public Float getTotalBill() {
        return this.totalBill;
    }
    public void readNotice(Notice notice) {
        this.viewedNotices.add(notice);
    }
    public Set<Notice> getNotices() {
        return this.game.getNotices();
    }
    public Set<Notice> getUnreadNotices() {
        return this.getNotices()
                    .stream()
                    .filter(n -> !viewedNotices.contains(n))
                    .collect(Collectors.toSet());
    }
}
