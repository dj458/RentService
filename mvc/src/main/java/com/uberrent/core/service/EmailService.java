package com.uberrent.core.service;

import com.uberrent.core.domain.User;
import com.uberrent.core.mail.RegistrationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmailService {
    @Autowired
    private RegistrationEmail registrationEmail;

    @Transactional
    public void sendEmailConfirmation(User user ) {
        registrationEmail.confirmEmail(user, null);

    }
}
