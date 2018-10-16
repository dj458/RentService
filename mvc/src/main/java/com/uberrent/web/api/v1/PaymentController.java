package com.uberrent.web.api.v1;

import com.uberrent.core.domain.Payment;
import com.uberrent.core.domain.User;
import com.uberrent.core.service.PaymentService;
import com.uberrent.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/payments",produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Payment addPaymentMethod(@RequestBody Payment payment){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        payment.setUser(user);
        paymentService.addPaymentMethod(payment);
        logger.info("save payment");
        return payment;
    }

    //get payment by userid, user inside the payment.
    @RequestMapping(value = "/id",params = "userid", method = RequestMethod.GET)
    @ResponseBody
    public Payment getPaymentByUserId(@RequestParam("userid") String userid){
        Payment payment=paymentService.findPaymentByUserid(userid);
        return payment;
    }
}