import java.util.Date;

public class Payment {
    private String id; // system generated
    private Float amount;
    private Date time;
    private PaymentMethod paymentMethod;
    private String receiptNumber; // bill ref number
    private Booking booking;
    private User user;

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public Date getTime() {
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

enum PaymentMethod {
    MPESA, CHEQUE, DIRECTDEPOSIT, CASH
}


