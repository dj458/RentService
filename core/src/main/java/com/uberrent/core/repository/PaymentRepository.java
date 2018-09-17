package com.uberrent.core.repository;

import com.uberrent.core.domain.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    @Query("select a from Payment a where a.cardNumber=?1")
    Optional<Payment> findByCardNumber(String cardNumber);

}
