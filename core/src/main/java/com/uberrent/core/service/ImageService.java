package com.uberrent.core.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    private StorageService storageService;

    public String upload(MultipartFile image) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        File dest = new File(randomUUIDString);
        String objUrl=null;
        try {
            image.transferTo(dest);
            String s3Key = FilenameUtils.getBaseName(image.getOriginalFilename())+"-"+randomUUIDString+"."+FilenameUtils.getExtension(image.getOriginalFilename());
            storageService.putObjectRequest(s3Key, dest);
            objUrl=  storageService.getObjectUrl(s3Key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objUrl;
    }
}
