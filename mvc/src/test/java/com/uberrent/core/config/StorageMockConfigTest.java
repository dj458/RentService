package com.uberrent.core.config;

import com.amazonaws.services.s3.AmazonS3;
import com.uberrent.core.service.StorageService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StorageMockConfigTest {
    @Value("${aws.s3.bucket}")
    private String bucket;

//    @Bean
//    @Primary
//    public StorageService getStorageService() {
//        AmazonS3 s3Client = Mockito.mock(AmazonS3.class);
//        StorageService beanStroageService = new StorageService(s3Client);
//        beanStroageService.setBucket(bucket);
//        return beanStroageService;
//    }
}

