package com.sportify.application.services.payment;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.payment.Payment;
import com.sportify.application.data.repository.payment.PaymentRepository;

@Service
public class PaymentService {
    public final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void savePayment(Payment payment) {
        if (payment == null) {
            System.err.println("Payment is null.");
            return;
        }
        paymentRepository.save(payment);
    }
}
