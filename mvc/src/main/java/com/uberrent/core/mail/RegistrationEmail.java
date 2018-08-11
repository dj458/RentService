package com.uberrent.core.mail;

import com.uberrent.core.domain.User;

import java.io.Serializable;
import java.util.Map;

public class RegistrationEmail extends AbstractFreemarkerEmail {
    @Override
    protected String getMailSubject() {
        String welcomeMessage= "Welcome to RentService";
        return welcomeMessage;
    }

    @Override
    protected void putValue(User user, Map<String, Serializable> root) {
        //String token = user.getConfirmToken();
        //String url = getMainUrl() + "/users/verification?confirmation_token=" + token;
        //root.put("activation_url", url);
        root.put("url", getMainUrl());

    }
}
