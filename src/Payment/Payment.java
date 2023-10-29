package Payment;
import java.sql.Timestamp;
// import java.util.concurrent.atomic.AtomicLong;

import Booking.Booking;
import Enum.PaymentMethod;
import User.User;
import Utils.CreateID;
import Utils.Timestamped;

public class Payment implements Timestamped{
    private String id; // system generated
    private Float amount;
    private Timestamp time;
    private PaymentMethod paymentMethod;
    private String receiptNumber; // bill ref number
    private Booking booking;
    private User user;
    
//  // Mechanism to get new ids
//     private static AtomicLong idCounter = new AtomicLong();

//     public static String createID() {
//     return String.valueOf(idCounter.getAndIncrement());
//     }


    public Payment (User u, Booking b, PaymentMethod m, String r, Float a) {
        // Timestamp t = new Timestamp(System.currentTimeMillis());

        this.amount = a;
        this.time = now();
        this.user = u;
        this.booking = b;
        this.paymentMethod = m;
        this.receiptNumber = r;
        this.id = CreateID.createID();
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public String getReceiptNumber() {
        return this.receiptNumber;
    }

    public Float getAmount() {
        return this.amount;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }
}



