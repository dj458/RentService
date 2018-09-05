package com.uberrent.web.api.v1;


import com.uberrent.core.domain.Image;
import com.uberrent.core.domain.User;
import com.uberrent.core.service.ImageService;
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
@RequestMapping(value="/api/image",produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public Image uploadImage(@RequestParam("pic") MultipartFile image){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username  = authentication.getName();
        User user = userService.findByUsername(username);
        logger.info("show image name "+ image.getName());
        String imageUrl=imageService.upload(image);
        Image newImage=new Image();
        newImage.setUrl(imageUrl);
        newImage.setUser(user);
        return newImage;
    }
}
