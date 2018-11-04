package com.uberrent.core.service;


import com.uberrent.core.domain.Authority;
import com.uberrent.core.domain.User;
import com.uberrent.core.repository.AuthorityRepository;
import com.uberrent.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private User findBy(User user){
        Optional<User> optional = userRepository.findById(user.getId());
        User result = optional.get();
        return result;
    }

    public User findById(Long id) {
         User user= userRepository.findById(id).get();
         return user;
    }

    public User findByUsername(String username) {
        User user=userRepository.findByUsername(username).get();
        return user;
    }

    public User findByEmailOrUsername(String usernameOrEmail){
        User user=userRepository.findUsernameOrEmail(usernameOrEmail).get();
        return user;
    }

   public List<Authority> findAuthorities(User user){
       List<Authority> authorities=authorityRepository.findAuthoritiesByUserId(user.getId());
        return authorities;
   }

    public Authority addAuthorityTo(User user){
        Authority authority = new Authority();
        authority.setRole("REGISTERED_USER");
        authority.setUser(user);
        return authorityRepository.save(authority);
    }

    public Authority changeAuthority(User user, String role){
        Authority authority= new Authority();
        authority.setRole(role);
        authority.setUser(user);
        return authorityRepository.save(authority);
    }

   public User registerUser(User user){
       save(user);
       addAuthorityTo(user);
       return user;
   }

    public User save(User u) {
        u.setAccountExpired(false);
        u.setAccountLocker(false);
        u.setCredentialsExpired(false);
        u.setEnabled(true);
        String encodedPassword = encoder.encode(u.getPassword()); //encry password String
        u.setPassword(encodedPassword);//put encodedPassword to password String
        User user=userRepository.save(u);
        return user;
    }
}
