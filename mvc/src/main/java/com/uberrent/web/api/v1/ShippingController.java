package com.uberrent.web.api.v1;

import com.uberrent.core.domain.Shipment;
import com.uberrent.core.domain.User;
import com.uberrent.core.service.ShipmentService;
import com.uberrent.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/shipments", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShippingController {

    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Shipment addShipment(@RequestBody Shipment shipment){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        shipment.setUser(user);
         shipment=shipmentService.addShipment(shipment);
        return shipment;
    }
}
