package com.sportify.application.data.entity.payment;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.enums.PaymentMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Payment extends AbstractEntity {
    @Column(updatable = false)
    private Float amount;
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp payTime;
    @Column(updatable = false)
    private PaymentMethod paymentMethod;
    @Column(updatable = false)
    private String receiptNumber;
    @ManyToOne
    @Column(updatable = false)
    private Booking booking;
    @ManyToOne
    @Column(updatable = false)
    private BUser user;

    public Payment () {}

    public Payment(BUser user, Booking booking,
            PaymentMethod method, String receipt,
            Float amount) {
        this.user = user;
        this.booking = booking;
        this.paymentMethod = method;
        this.receiptNumber = receipt;
        this.amount = amount;
    }

    public float getAmount() {
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

    public void setAmount(Float amount) {
        if (this.amount == null) {
            this.amount = amount;
        }
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
