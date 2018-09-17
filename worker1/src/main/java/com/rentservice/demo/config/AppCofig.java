package com.rentservice.demo.config;

import com.uberrent.core.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.env.Environment;

@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackages = "com.rentservice.demo")
public class AppCofig {
    @Autowired
    private Environment env;

    @Bean(name = "appProperties")
    public PropertiesFactoryBean getDatabaseConfig() throws Exception {
        String profile = env.getActiveProfiles()[0];
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocation(new ClassPathResource("META-INF/env/" + profile + "-app.properties"));
        return factory;
    }

    @Bean(name = "shareProperties")
    public PropertiesFactoryBean getShareProperties() throws Exception {
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocation(new ClassPathResource("META-INF/share.properties"));
        return factory;
    }
}