package com.uberrent.core.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class ImageService {
    @Autowired
    private StorageService storageService;

    public void upload(MultipartFile image) {

        File dest = new File("/tmp/image.png");
        try {
            image.transferTo(dest);
            storageService.putObjectRequest(image.getOriginalFilename(), dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
