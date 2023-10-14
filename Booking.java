import java.util.Date;
import java.util.List;

import javax.management.Notification;

public class Booking {
    private int id;
    private User user;
    private Game game;
    private Date bookingDate;
    private boolean attended;
    private boolean cancelled;
    private List<String> seats;
    private List<Notification> notices;
    private boolean paidInFull;
    private List<Payment> payments;
    private List<Notification> viewedNotices;

    public void makeBooking(User user, Game game) {
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

    public void attend() {
        this.attended = true;
    } //

    public boolean cancel() {
        this.cancelled = true; // Needs improvement, 
        return true;
    }

    public boolean pay(Float amount) {
        // Payment payment = new Payment('User, Booking, PaymentMethod, Amount, ReceiptNumber'); // More improvement
        return  true; // Return true/false on success
    }

    public boolean isPaidInFull() {
        return this.paidInFull;
    }

    public void readNotification(Notification notice) { // more research
        this.viewedNotices.add(notice);
    }
}
