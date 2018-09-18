package com.uberrent.web.api.v1;

import com.uberrent.core.domain.Payment;
import com.uberrent.core.domain.User;
import com.uberrent.core.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/payments",produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Payment addPaymentMethod(@RequestBody Payment payment){
        paymentService.addPaymentMethod(payment);
        logger.info("save payment");
        return payment;
    }
}
