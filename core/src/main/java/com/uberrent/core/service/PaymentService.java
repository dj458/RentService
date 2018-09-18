package com.uberrent.core.service;

import com.uberrent.core.domain.Payment;
import com.uberrent.core.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment findByCardNumber(String cardnumber){
        Payment payment=paymentRepository.findByCardNumber(cardnumber).get();
        return payment;
    }

    public Payment addPaymentMethod(Payment payment){
        save(payment);
        return payment;
    }

    public Payment save(Payment p){
        p.setPaymentType();
        Payment payment= paymentRepository.save(p);
        return payment;

    }
}
