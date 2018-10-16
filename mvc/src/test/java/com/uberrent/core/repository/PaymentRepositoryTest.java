package com.uberrent.core.repository;

import com.uberrent.core.domain.Payment;
import com.uberrent.core.domain.User;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
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
        assertEquals(p.getCardNumber(),paymentTest.get().getCardNumber());
    }

    @Test
    @Transactional
    public void findByUserid(){
        User b =new User();
        b.setFirstName("feng");
        b.setUsername("fzheng1");
        b.setLastName("zheng");
        b.setAccountExpired(false);
        b.setEmail("fzheng8@vt.edu");
        b.setAccountLocker(false);
        b.setAccountExpired(false);
        b.setCredentialsExpired(false);
        b.setEnabled(true);
        b.setPassword("123");
        userRepository.save(b);

        Payment p = new Payment();
        p.setCardNumber("123");
        p.setPaymentValue("12");
        p.setPaymentType("Visa");
        p.setUser(b);
        paymentRepository.save(p);
        List<Payment> paymentTest2=paymentRepository.findByUserId(b.getId());
        assertNotNull(paymentTest2);
        assertEquals(p.getCardNumber(),paymentTest2.get(0).getCardNumber());
    }
}
