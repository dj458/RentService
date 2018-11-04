package com.uberrent.core.sms;


// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACc53424a54dcad2e725fb78d8759fb4c2";
    public static final String AUTH_TOKEN =
            "25fedeb9db81022a9a82a6e8e72c319a";

  //  public static void main(String[] args) {

      public void sendSMS(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+15404496270"), // to
                        new PhoneNumber("+15405180329"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());
    }
}