package com.uberrent.core.repository;

import com.uberrent.core.domain.Authority;
import com.uberrent.core.domain.User;

import com.uberrent.web.extend.security.Utils;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UtilsTest extends RepositoryTest {
@Test
@Transactional
    public void utilsListTest(){

    User b = new User();
    b.setFirstName("Zheng");
    b.setUsername("fzheng89850340we");
    b.setLastName("Feng");
    b.setAccountExpired(false);
    b.setEmail("fzheng8324qe");
    b.setAccountLocker(false);
    b.setAccountExpired(false);
    b.setCredentialsExpired(false);
    b.setEnabled(true);
    b.setPassword("123");
    userRepository.save(b);

    Authority a = new Authority();
    a.setUser(b);
    a.setRole("guest");
    authorityRepository.save(a);

    List<Authority> expectedAuthority = authorityRepository.findAuthoritiesByUserId(a.getUser().getId());
    Collection<GrantedAuthority> actualList =Utils.getAuthorities(expectedAuthority);
//    List<GrantedAuthority> listNew = new ArrayList<GrantedAuthority>();
    for(GrantedAuthority auth:actualList){
        assertEquals(expectedAuthority.get(0).getRole().toUpperCase(),auth.getAuthority());
    }
//    assertEquals(a.getRole().toUpperCase(),listNew.get(0).getAuthority());
}
}
