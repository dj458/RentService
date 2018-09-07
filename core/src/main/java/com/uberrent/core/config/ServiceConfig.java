package com.uberrent.core.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.uberrent.core.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.uberrent.core")
public class ServiceConfig {
    @Value("${aws.s3.bucket}")
    private String bucket;

    @Bean
    public StorageService getStorageService() throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService beanStroageService = new StorageService(s3Client);
//        Properties properties = factoryBean.getObject();
//        String bucket = properties.getProperty(bucketPropertyName);
        beanStroageService.setBucket(bucket);
        return beanStroageService;
    }
}

