package com.uberrent.web.api.v1;


import com.uberrent.core.domain.Image;
import com.uberrent.core.domain.User;
import com.uberrent.core.enumdef.ObjectUrlFormat;
import com.uberrent.core.repository.ImageRepository;
import com.uberrent.core.service.ImageService;
import com.uberrent.core.service.StorageService;
import com.uberrent.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/api/images",produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public Image uploadImage(@RequestParam("pic") MultipartFile image){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username  = authentication.getName();
        User user = userService.findByUsername(username);
        logger.info("show image name "+ image.getName());
        Image imageUrl=imageService.upload(image);
        imageUrl.setUser(user);
        imageRepository.save(imageUrl);
        return imageUrl;
    }

    @RequestMapping(value = "/{Id}",params = "format",method = RequestMethod.GET)
    @ResponseBody
    public Image getImageURL(@RequestParam("format") ObjectUrlFormat format, @PathVariable("Id") Long Id){
        Image image=imageRepository.findByImageId(Id).get();
        String preSignedS3Key= image.getS3Key();
        if (format==ObjectUrlFormat.REGULAR) {
            String url= imageService.getUrl(preSignedS3Key);
            image.setUrl(url);
        } else {
            String url= imageService.getPreSignedUrl(preSignedS3Key).toString();
            image.setUrl(url);
        }
        return image;
    }
}