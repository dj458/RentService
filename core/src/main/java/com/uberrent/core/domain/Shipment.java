package com.uberrent.core.domain;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "shipment")
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy=SEQUENCE,generator="shipment_id_seq")
    @SequenceGenerator(name="shipment_id_seq",sequenceName="shipment_id_seq",allocationSize = 1)
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private User user;

    @Column (name = "shipment_speed")
    private String shipmentSpeed;

    @Column(name = "shipment_cost")
    private String shipmentCost;

    @Column (name = "shipment_weight")
    private String shipmentWeight;

    public String getShipmentSpeed(){return shipmentSpeed;}
    public void setShipmentSpeed(String shipment_speed ){this.shipmentSpeed=shipment_speed;}

    public String getShipmentCost(){return shipmentCost;}
    public void setShipmentCost(String shipment_cost){this.shipmentCost=shipment_cost;}

    public String getShipmentWeight(){return shipmentWeight;}
    public void setShipmentWeight(String shipment_weight){this.shipmentWeight=shipment_weight;}

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}

    public long getId() { return id; }
}
