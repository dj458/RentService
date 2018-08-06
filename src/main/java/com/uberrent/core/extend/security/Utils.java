package com.uberrent.core.extend.security;

import com.uberrent.core.domain.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {
    public static Collection<GrantedAuthority> getAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for (Authority auth : authorities){
            String ROLE = auth.getRole().toUpperCase();
            authList.add(new SimpleGrantedAuthority(ROLE));
        }
        return authList;
    }
}
