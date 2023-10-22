import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Booking extends CreateID implements Timestamped{
    private String id;
    private User user;
    private Game game;
    private Date bookingDate;
    private boolean attended;
    private boolean cancelled;
    private List<Reservation> reservations;
    private List<Notification> notices;
    private boolean paidInFull;
    private List<Payment> payments;
    private List<Notification> viewedNotices;

    public Booking (User u,
                    Game g,
                    List<Reservation> r) {
        this.id = createID();
        this.user = u;
        this.game = g;
        this.reservations = r;
        this.bookingDate = today();
        this.notices = new LinkedList<Notification>();
        this.payments = new LinkedList<Payment>();
        this.viewedNotices = new LinkedList<Notification>();
        this.attended = false;
        this.cancelled = false;        
    }

    public void makePayment(Float amount, 
                            PaymentMethod method,
                            String receipt ) {
        var pmt = new Payment(this.user, this, method, 
                            receipt, amount);
        this.payments.add(pmt);
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
