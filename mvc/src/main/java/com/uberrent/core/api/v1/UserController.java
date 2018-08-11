package com.uberrent.core.api.v1;

import com.uberrent.core.domain.Image;
import com.uberrent.core.domain.User;
import com.uberrent.core.enumdef.WorkerMessageType;
import com.uberrent.core.extend.security.JwtTokenUtil;
import com.uberrent.core.service.EmailService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping(value="/api/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private  EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public List getUserList(){
        logger.info("test1234");
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public User postUser(@RequestBody User user){
        User userSaved = userService.save(user);
        return userSaved;
    }


    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("Id") Long Id){
        User user = userService.findById(Id);
        return user;
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User signupUser(@RequestBody User user) {
             userService.registerUser(user);
             logger.info("save user "+ user.getUsername());
             return user;
    }

    @RequestMapping(value = "/search",params = "username", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByParam(@RequestParam("username") String username){
        logger.info("request parameters " + username);
        User user = userService.findByUsername(username);
        return user;
    }

    @RequestMapping(value = "/search",params = "email", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByParam_Second(@RequestParam("email") String username){
        logger.info("request parameters " + username);
        User user = userService.findByUsername(username);
        return user;
    }

    @RequestMapping(value = "/login",params = "username",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestParam ("username")
                           String username,
                                    @RequestParam("password")
                            String password,
                                       Device device){
        logger.info("login parameters " +username);
        try {
            Authentication notFullyAuthentication = new UsernamePasswordAuthenticationToken(username, password);
            Authentication fullyAuthentication = authenticationManager.authenticate(notFullyAuthentication);
            SecurityContextHolder.getContext().setAuthentication(fullyAuthentication);

            UserDetails userDetails =  userService.findByUsername(username);
            String token=  jwtTokenUtil.generateToken(userDetails, device);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch (AuthenticationException e )
        {
            logger.error("Exception occurred",e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failure,check your username");
        }


    @RequestMapping(value = "/image",method = RequestMethod.POST)
    @ResponseBody
    public Image uploadImage(@RequestParam("pic") MultipartFile image){
        logger.info("show image name "+ image.getName());
        return null;
    }

    @RequestMapping(value = "/{id}/email",method = RequestMethod.POST)
    public void sendEmailConfirmation(@PathVariable("id") Long id){
       //User user= userService.findById(id);
       messageService.sendMessage(WorkerMessageType.UserSignUpMsg,String.valueOf(id),5000);
       //emailService.sendEmailConfirmation(user);
    }
}
