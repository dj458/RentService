package com.uberrent.core.service;

import com.uberrent.core.domain.Authority;
import com.uberrent.core.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired AuthorityRepository authorityRepository;

    public List<Authority> findAuthoritiesByUserId(Long userID){
        List<Authority> authorities = authorityRepository.findAuthoritiesByUserId(userID);
        return authorities;
    }
}
