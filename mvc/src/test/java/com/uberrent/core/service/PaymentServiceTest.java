//package com.uberrent.core.service;
//
//import com.uberrent.core.domain.Payment;
//import com.uberrent.core.domain.User;
//import com.uberrent.core.repository.PaymentRepository;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
//import static org.junit.Assert.assertNotNull;
//
//public class PaymentServiceTest {
//    @Autowired
//    private PaymentService paymentService;
//    @Autowired
//    private PaymentRepository paymentRepository;
//    @Test
//    @Transactional
//    public void findByCardNumber(){
//        Payment p = new Payment();
//        p.setPaymentType("Visa");
//        p.setPaymentValue("8");
//        p.setCardNumber("123");
//        paymentRepository.save(p);
//        Optional<Payment> testPayment = paymentRepository.findByCardNumber(p.getCardNumber());
//        assertNotNull(testPayment);
//    }
//}
