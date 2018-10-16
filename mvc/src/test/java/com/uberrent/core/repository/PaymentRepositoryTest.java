package com.uberrent.core.repository;

import com.uberrent.core.domain.Payment;
import org.junit.Test;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

public class PaymentRepositoryTest extends RepositoryTest {
    @Test
    @Transactional
    public void findByCardNumber(){
        Payment p = new Payment();
        p.setCardNumber("123");
        p.setPaymentValue("12");
        p.setPaymentType("Visa");
        paymentRepository.save(p);
        Optional<Payment> paymentTest = paymentRepository.findByCardNumber(p.getCardNumber());
        assertNotNull(paymentTest);
    }

}
