package com.uberrent.web.api.v1;

import com.uberrent.core.domain.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/payment",produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private Logger logger=LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "payment-method",method = RequestMethod.POST)
    public Payment addPaymentMethod(@RequestBody Payment payment){

        logger.info("This is payment test");
        return payment;
    }
}
