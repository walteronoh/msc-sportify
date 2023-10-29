package Booking;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Enum.PaymentMethod;
import Event.Game;
import Payment.Payment;
import User.User;
import Utils.CreateID;
import Utils.Timestamped;
import Notification.Notification;

public class Booking  implements Timestamped{
    private String id;
    private User user;
    private Game game;
    private Date bookingDate;
    private boolean attended;
    private boolean cancelled;
    private List<Reservation> reservations;
    private boolean madeReservations;
    private float totalBill;
    // private List<Notification> notices; // Added this to the Game class. Discuss Idea of Notifiable Interface.
    private boolean paidInFull;
    private List<Payment> payments;
    private List<Notification> viewedNotices;

    public Booking (User u,
                    Game g,
                    List<Reservation> r) {
        this.id = CreateID.createNamedID("BN");
        this.user = u;
        this.game = g;
        this.reservations = r;
        this.madeReservations = g.makeReservations(r);
        var sum = 0;
        for(Reservation rv: r) {
            sum += rv.getCost();
        }
        this.totalBill = sum;
        this.bookingDate = today();
        this.payments = new ArrayList<Payment>();
        this.viewedNotices = new ArrayList<Notification>();
        this.attended = false;
        this.cancelled = false;        
    }

    public void makePayment(Float amount, 
                            PaymentMethod method,
                            String receipt ) {
        var pmt = new Payment(this.user, this, method, 
                            receipt, amount);
        this.payments.add(pmt);

        var totalPaid = 0;
        for(Payment p: this.payments) {
            totalPaid += p.getAmount();
        }
        if (totalPaid >= totalBill) {
            this.paidInFull = true;
        }
    }

    public boolean reserve() {
        if(!this.madeReservations) {
            this.madeReservations = this.game.makeReservations(this.reservations);
        }
        return this.madeReservations;
    }

    public String getID() {
        return this.id;
    } 
    public User getUser() {
        return this.user;
    }

    public Date getBookingDate() {
        return this.bookingDate;
    }

    public Game getGame() {
        return this.game;
    }

    public List<Payment> getPayments() {
        return this.payments;
    }
    public void attend() {
        this.attended = true;
    } 
    public boolean getAttended() {
        return this.attended;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
    public boolean cancel() {
        return this.game.cancelReservations(this.reservations);
    }

    public boolean isPaidInFull() {
        return this.paidInFull;
    }

    public void readNotification(Notification notice) { // more research
        this.viewedNotices.add(notice);
    }
    public List<Notification> geNotifications() {
        return this.game.getNotices();
    }
    public List<Notification> getUnreadNotices() {
        var all = this.geNotifications();
        var read = this.viewedNotices;

        var diff = all.stream()
                      .filter(n -> !read.contains(n))
                      .collect(Collectors.toList());
        return diff; // Consider memoization to improve performance contains s method quite expensive
    }
}
