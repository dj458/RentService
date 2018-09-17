package com.uberrent.core.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column (name = "payment_type")
    private String payment_type;

    @Column (name = "cardNumber")
    private String cardNumber;

    @Column(name ="payment_value")
    private String payment_value;

    public void setPaymentType(){this.payment_type=payment_type;}
    public String getPaymentType(){return payment_type;}

    public void setCardNumber(){this.cardNumber=cardNumber;}
    public String getCardNumber(){return cardNumber;}

    public void setPaymentValue(){this.payment_value=payment_value;}
    public String getPaymentValue(){return payment_value;}

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;}

    public Long getId() { return id; }
}
