package com.uberrent.core.service;

import com.amazonaws.services.s3.AmazonS3;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class ImageService {
    private AmazonS3 s3;
    @Autowired
    private StorageService storageService;

    public String upload(MultipartFile image) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        File dest = new File(randomUUIDString);
        String objUrl=null;
        String StringimagePreSignedUrl=null;
        try {
            image.transferTo(dest);
            String s3Key = FilenameUtils.getBaseName(image.getOriginalFilename())+"-"+randomUUIDString+"."+FilenameUtils.getExtension(image.getOriginalFilename());
            URL imagePreSignedUrl=  storageService.getPreSignedUrl(s3Key);
            storageService.putObjectRequest(s3Key, dest);
            objUrl=  storageService.getObjectUrl(s3Key);
             StringimagePreSignedUrl=imagePreSignedUrl.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  StringimagePreSignedUrl;

    }
}
