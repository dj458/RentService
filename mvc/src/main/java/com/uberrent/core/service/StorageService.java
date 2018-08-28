package com.uberrent.core.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;

public class StorageService {
    private AmazonS3 s3;
    private String bucket;


    @Value("${aws.region}")
    private String region;

    public StorageService (AmazonS3 s3){
        this.s3=s3;
    };

    public void putObjectRequest(String s3Key,  File image){
        s3.putObject(bucket,s3Key, image);
    }
    public String getBucket(){return bucket;}
    public void setBucket(String bucket){this.bucket=bucket;}

    public String getObjectUrl(String s3key){
        String objectUrl="https://s3."+region+".amazonaws.com/"+bucket+"/"+s3key;
        return objectUrl;
    }
}