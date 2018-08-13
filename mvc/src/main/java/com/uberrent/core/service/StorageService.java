package com.uberrent.core.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public class StorageService {
    private AmazonS3 s3;
    private String bucket;
    public StorageService (AmazonS3 s3){
        this.s3=s3;
    };
    
    public void putObjectRequest(String s3Key,  File image){
        s3.putObject(bucket,s3Key, image);
    }
    public String getBucket(){return bucket;}
    public void setBucket(String bucket){this.bucket=bucket;}


}
