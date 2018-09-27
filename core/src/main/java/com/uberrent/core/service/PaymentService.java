package com.uberrent.core.service;

import com.uberrent.core.domain.Payment;
import com.uberrent.core.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment findByCardNumber(String cardnumber){
        Payment payment=paymentRepository.findByCardNumber(cardnumber).get();
        return payment;
    }

    @Transactional
    public Payment addPaymentMethod(Payment payment){
        //save(payment);
        Payment payment1= paymentRepository.save(payment);
        return payment1;
    }

//    public Payment save(Payment p){
//        p.setPaymentType();
//        p.setPaymentValue();
//        p.setCardNumber();
//        Payment payment= paymentRepository.save(p);
//        return payment;
//
//    }
}
