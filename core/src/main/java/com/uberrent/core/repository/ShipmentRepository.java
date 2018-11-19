package com.uberrent.core.repository;

import com.uberrent.core.domain.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment,Long> {

    @Query("select a from Shipment a where a.shipmentCost=?1")
    Optional<Shipment> findByShipmentCost(String shipmentCost);
}
