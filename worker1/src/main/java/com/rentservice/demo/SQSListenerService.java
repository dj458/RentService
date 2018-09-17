package com.rentservice.demo;

import com.uberrent.core.domain.User;
import com.uberrent.core.service.EmailService;
import com.uberrent.core.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import javax.jms.JMSException;
import java.util.Map;

@Service
public class SQSListenerService{
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "rentservice_dev")
    public void createThumbnail(Map<String,Object> msg) throws JMSException {
        String id  =(String)msg.get("msgText");
        Long id2=Long.parseLong(id);
        User user= userService.findById(id2);
        emailService.sendEmailConfirmation(user);
        log.info("Received");
    }
}