package com.uberrent.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.uberrent.web.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")

//@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class StorageServiceTest {
   @InjectMocks
    @Autowired
    private StorageService storageService;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Mock
    private AmazonS3 client=Mockito.mock(AmazonS3.class);

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception{
        validateMockitoUsage();
    }

    @Test
    public void getObjectUrlTest(){
        String s3key="IMG_0385+2.JPG";
        String expectedUrl="https://s3.us-east-2.amazonaws.com/rent-service-dev/"+s3key;
        String actualUrl=storageService.getObjectUrl(s3key);
        assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void getPreSignedUrlTest(){
        storageService.getPreSignedUrl("IMG_0383-02208211-a987-4f3c-9822-a3ad6bc3b99a.JPG");
        Mockito.verify(client, times(1)).generatePresignedUrl(any());
    }
}
