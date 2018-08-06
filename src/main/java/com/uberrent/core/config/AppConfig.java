package com.uberrent.core.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.uberrent.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages="com.uberrent.core")
public class AppConfig {
    @Autowired
    private Environment env;
    private String bucketPropertyName="s3.bucket";

    @Bean(name = "appProperties")
    public PropertiesFactoryBean getDatabaseConfig()throws Exception {
        String profile = env.getActiveProfiles()[0];
        PropertiesFactoryBean factory=new PropertiesFactoryBean();
        factory.setLocation(new ClassPathResource("META-INF/env/"+profile+"-app.properties"));
        return factory;
    }

    @Bean (name = "shareProperties")
    public PropertiesFactoryBean getShareProperties()throws Exception {
        PropertiesFactoryBean factory=new PropertiesFactoryBean();
        factory.setLocation(new ClassPathResource("META-INF/share.properties"));
        return factory;
    }


    public StorageService getStorageService(@Autowired @Qualifier("appProperties") PropertiesFactoryBean factoryBean) throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService beanStroageService = new StorageService(s3Client);
        Properties properties = factoryBean.getObject();
        String bucket=properties.getProperty(bucketPropertyName);
        beanStroageService.setBucket(bucket);
        return  beanStroageService;
    }
}

