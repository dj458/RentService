package com.uberrent.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.uberrent.core.domain.Image;
import com.uberrent.core.repository.ImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class ImageService {
    private AmazonS3 s3;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserService userService;

    @Transactional
    //TODO save image for transaction behavior
    public Image upload(MultipartFile multiPartFile) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String fileName = FilenameUtils.getBaseName(multiPartFile.getOriginalFilename())+"-"+randomUUIDString+"."+FilenameUtils.getExtension(multiPartFile.getOriginalFilename());
        File dest = new File(fileName);
        Image img=new Image();
        try {
            multiPartFile.transferTo(dest);
            storageService.putObjectRequest(fileName, dest);
            img.setS3Key(fileName);
        } catch (IOException e) {
            e.printStackTrace();
//            logger
        }
        return  img;
    }

    public String getUrl(String fileName){
        return storageService.getObjectUrl(fileName);
    }

    public URL getPreSignedUrl(String fileName){
        return  storageService.getPreSignedUrl(fileName);
    }
}
