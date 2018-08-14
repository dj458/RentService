package com.uberrent.core.service;

import com.uberrent.core.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class StorageServiceTest {
    @Autowired
    private StorageService storageService;
    @Test
    public void getObjectUrlTest(){
        String s3key="IMG_0385+2.JPG";
        String expectedUrl="https://s3.us-east-1.amazonaws.com/rent-service-dev/"+s3key;
        String actualUrl=storageService.getObjectUrl(s3key);
        assertEquals(expectedUrl,actualUrl);
    }
}
