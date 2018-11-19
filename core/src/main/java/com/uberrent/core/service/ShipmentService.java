package com.uberrent.core.service;

import com.uberrent.core.domain.Shipment;
import com.uberrent.core.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;


    public Shipment addShipment(Shipment shipment){
        return shipmentRepository.save(shipment);
    }
}
