package com.sportify.application.data.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
