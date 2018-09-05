package com.uberrent.core.service;


import com.uberrent.web.config.AppConfig;
import com.uberrent.core.domain.User;
import com.uberrent.core.mail.RegistrationEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class EmailServiceTest {
    @Autowired
    private RegistrationEmail registrationEmail;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Test
    @Transactional
    public void confirmEmailTest(){
        when(javaMailSender.createMimeMessage()).thenReturn(Mockito.mock(MimeMessage.class));
        User u = new User();
        u.setFirstName("Zheng");
        u.setUsername("fzheng8");
        u.setLastName("Feng");
        u.setAccountExpired(false);
        u.setEmail("fzheng8");
        u.setAccountLocker(false);
        u.setAccountExpired(false);
        u.setCredentialsExpired(false);
        u.setEnabled(true);
        u.setPassword("123");

        Map root =new HashMap<String, Serializable>();
        registrationEmail.confirmEmail(u,root);
        Mockito.verify(javaMailSender, times(1)).createMimeMessage();
    }
}
