package com.uberrent.core.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table (name ="equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "equipments_id_seq")
    @SequenceGenerator(name = "equipments_id_seq", sequenceName = "equipments_id_seq")
    private Long id;

    @Column(name = "price_value")
    private String priceValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public Long getId() {
        return id;
    }
}
