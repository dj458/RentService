package com.rentservice.demo;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import javax.jms.JMSException;
import java.io.IOException;

@Service
public class SQSListenerService{

    private Logger log = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = "rentservice_dev")
    public void createThumbnail(String requestJSON) throws JMSException {
        log.info("Received ");

    }
}