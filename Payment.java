import java.util.Date;

public class Payment {
    private String id; // system generated
    private Float amount;
    private Date time;
    private Enum<PaymentMethod> paymentMethod;
    private String receiptNumber; // bill ref number
    private Booking booking;
    private User user;

    public String getId() {
    }

    public String getUser() {
    }

    public String getBooking() {
    }

    public String getTime() {
    }

    public String getReceiptNumber() {
    }

    public Float getAmount() {
    }

    public PaymentMethod getPaymentMethod() {
    }
}

enum PaymentMethod {
    MPESA, CHEQUE, DIRECTDEPOSIT, CASH
}


