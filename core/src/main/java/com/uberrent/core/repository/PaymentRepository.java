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

    //load payment info with related user info that's why use left join fetch
    @Query("select a from Payment a LEFT JOIN FETCH a.user u where u.id=?1")
//    @Query("select a from Payment a where a.user.id=?1")
    List<Payment> findByUserId(Long id);//user to payment (oneToMany), one user will have several payments
                                        //that why use list
}