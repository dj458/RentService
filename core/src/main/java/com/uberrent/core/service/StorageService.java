package com.uberrent.core.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.net.URL;

public class StorageService {

    private AmazonS3 s3;
    private String bucket;

    @Value("${aws.region}")
    private String region;

    @Value("#{shareProperties['pre.expiration']}")
    private Long preUrlexpiration;

    public StorageService (AmazonS3 s3){
        this.s3=s3;
    };

    public void putObjectRequest(String s3Key,  File image){
        s3.putObject(bucket, s3Key, image);
    }

    public String getBucket(){return bucket;}

    public void setBucket(String bucket){this.bucket=bucket;}

    public String getObjectUrl(String s3key){
        String objectUrl="https://s3."+region+".amazonaws.com/"+bucket+"/"+s3key;
        return objectUrl;
    }

    public S3Object getObject(String S3key) {
        if(S3key==null){
            return null;
        }else{
            return s3.getObject(bucket, S3key);
        }
    }
    public URL getPreSignedUrl(String s3Key){
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60;
        expiration.setTime(expTimeMillis);
        System.out.println("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, s3Key)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
        return url;
    }
}