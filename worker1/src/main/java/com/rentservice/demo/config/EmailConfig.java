package com.rentservice.demo.config;

import com.uberrent.core.mail.RegistrationEmail;
import com.uberrent.core.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Profile({"dev","test","prod"})
    @Bean(name="mailSender")
    public JavaMailSenderImpl getEmailSender(){
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost("smtp.sendgrid.net");
        emailSender.setPort(465);
        emailSender.setProtocol("smtps");
        emailSender.setUsername("djb4588");
        emailSender.setPassword("2018aN###");
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtps.auth","true");
        mailProperties.setProperty("mail.smtp.ssl.enable","true");
        mailProperties.setProperty("mail.transport.protocol","smtps");
        emailSender.setJavaMailProperties(mailProperties);
        return emailSender;
    }

    @Bean(name="freemarker")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerMailConfiguration(){
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:/WEB-INF/mail/");
        freeMarkerConfigurationFactoryBean.setPreferFileSystemAccess(false);
        return freeMarkerConfigurationFactoryBean;
    }

    @Bean
    public RegistrationEmail getRegistrationEmail(@Autowired JavaMailSenderImpl javaMailSender, @Autowired @Qualifier("freemarker") freemarker.template.Configuration configuration){
     RegistrationEmail registrationEmail = new RegistrationEmail();
     registrationEmail.setMailSender(javaMailSender);
     registrationEmail.setConfiguration(configuration);
     registrationEmail.setFreemarkerTemplate("RegistrationEmail.ftl");
     return  registrationEmail;
    }

    @Bean
    public EmailService getEmailService(){
        EmailService emailService=new EmailService();
        //emailService.sendEmailConfirmation(user);
        return emailService;
    }
}
