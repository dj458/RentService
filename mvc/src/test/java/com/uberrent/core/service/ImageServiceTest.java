package com.uberrent.core.service;

import com.uberrent.core.domain.Image;
import com.uberrent.web.config.AppConfig;
import com.uberrent.core.config.StorageMockConfigTest;
import com.uberrent.core.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,StorageMockConfigTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;
    @Test
    public void uploadTest(){
        String s3key="IMG_0385+2.JPG";
        MultipartFile multipartFile=Mockito.mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test.png");
        String expectedUrl=      "https://s3.us-east-2.amazonaws.com/rent-service-dev/test-.png";
        Image actualUrl=imageService.upload(multipartFile);
        String url=actualUrl.getUrl();
        boolean a=url.matches("https://s3.us-east-2.amazonaws.com/rent-service-dev/test-(.*).png");
        assertTrue(a);
    }
}
