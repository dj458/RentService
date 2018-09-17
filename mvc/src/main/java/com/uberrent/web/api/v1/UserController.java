package com.uberrent.web.api.v1;

import com.uberrent.core.domain.LoginInfo;
import com.uberrent.core.domain.User;
import com.uberrent.core.enumdef.WorkerMessageType;
import com.uberrent.web.extend.security.JwtTokenUtil;
import com.uberrent.core.service.EmailService;
import com.uberrent.core.service.ImageService;
import com.uberrent.core.service.StorageService;
import com.uberrent.core.service.UserService;
import com.uberrent.core.service.jms.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  MessageService messageService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User signupUser(@RequestBody User user) {
             userService.registerUser(user);
             logger.info("save user "+ user.getUsername());
             messageService.sendMessage(WorkerMessageType.UserSignUpMsg,String.valueOf(user.getId()),5000);
             return user;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> loginUser (@RequestBody LoginInfo loginInfo, Device device){
        logger.info("login parameters " +loginInfo.username);
        try {
            Authentication notFullyAuthentication = new UsernamePasswordAuthenticationToken(loginInfo.username, loginInfo.password);
            Authentication fullyAuthentication = authenticationManager.authenticate(notFullyAuthentication);
            SecurityContextHolder.getContext().setAuthentication(fullyAuthentication);

            UserDetails userDetails =  userService.findByUsername(loginInfo.username);
            String token=  jwtTokenUtil.generateToken(userDetails, device);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch (AuthenticationException e )
        {
            logger.error("Exception occurred",e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failure,check your username");
        }


    @RequestMapping(value = "/search",params = "username", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByParam(@RequestParam("username") String username){
        logger.info("request parameters " + username);
        User user = userService.findByUsername(username);
        return user;
    }

    @RequestMapping(value = "/{id}/email",method = RequestMethod.POST)
    public void sendEmailConfirmation(@PathVariable("id") Long id){

//       User user= userService.findById(id);
//       messageService.sendMessage(WorkerMessageType.UserSignUpMsg,String.valueOf(id),5000);
//       emailService.sendEmailConfirmation(user);
    }
}

