package com.uberrent.web.config;

import com.uberrent.core.config.DataBaseConfig;
import com.uberrent.core.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "com.uberrent.web")
@Import({ServiceConfig.class})
public class AppConfig {
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

