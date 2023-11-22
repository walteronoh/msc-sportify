package com.sportify.application.data.entity.payment;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.enums.PaymentMethod;

import jakarta.persistence.Entity;

@Entity
public class Payment extends AbstractEntity {

    public Payment(BUser user, Booking booking, PaymentMethod method, String receipt, Float amount) {
    }

    public float getAmount() {
        return 0;
    }
    
}
