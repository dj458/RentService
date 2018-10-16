package com.uberrent.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq",allocationSize=1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    //use JsonIgnore to ignore all the user info, because its LAZY FetchType so user info can't load and
    //we need to ignore it, so that findPaymentByUserId can work properly
//    @JsonIgnore
    private User user;

    @Column (name = "payment_type")
    private String paymentType;

    @Column (name = "card_number")
    private String cardNumber;

    @Column(name ="payment_value")
    private String paymentValue;

    public String getPaymentType(){return paymentType;}
    public void setPaymentType(String payment_type){this.paymentType=payment_type;}

    public String getCardNumber(){return cardNumber;}
    public void setCardNumber(String cardNumber){this.cardNumber=cardNumber;}

    public String getPaymentValue(){return paymentValue;}
    public void setPaymentValue(String payment_value){this.paymentValue=payment_value;}

    //public void setPaymentMethod(Payment payment){this.payment=payment;}
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;}

    public Long getId() { return id; }
}
