package com.sportify.application.data.entity.payment;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.enums.PaymentMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Payment extends AbstractEntity {
    @NotNull
    @Column(updatable = false)
    private double amount;
    @NotNull
    @Column(updatable = false)
    private PaymentMethod paymentMethod;
    @Column(updatable = false)
    private String receiptNumber;
    @ManyToOne
    private Booking booking;
    @ManyToOne
    private BUser user;

    public Payment() {
    }

    public Payment(BUser user, Booking booking,
            PaymentMethod method, String receipt,
            Float amount) {
        this.user = user;
        this.booking = booking;
        this.paymentMethod = method;
        this.receiptNumber = receipt;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public BUser getUser() {
        return user;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBooking(Booking booking) {
        if (this.booking == null) {
            this.booking = booking;
        }
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if (this.paymentMethod == null) {
            this.paymentMethod = paymentMethod;
        }
    }

    public void setReceiptNumber(String receiptNumber) {
        if (this.receiptNumber == null) {
            this.receiptNumber = receiptNumber;
        }
    }

    public void setUser(BUser user) {
        if (this.user == null) {
            this.user = user;
        }
    }
}
