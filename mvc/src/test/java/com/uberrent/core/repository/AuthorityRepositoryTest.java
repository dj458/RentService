package com.uberrent.core.repository;

import com.uberrent.core.domain.Authority;
import com.uberrent.core.domain.User;
import org.junit.Test;
import javax.transaction.Transactional;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthorityRepositoryTest extends RepositoryTest {
   @Test
   @Transactional
    public void findAuthoritiesByUserIdTest(){
       Authority a = new Authority();
       a.setRole("guest");
       User b = new User();
       b.setFirstName("Zheng");
       b.setUsername("fzheng8");
       b.setLastName("Feng");
       b.setAccountExpired(false);
       b.setEmail("fzheng8");
       b.setAccountLocker(false);
       b.setAccountExpired(false);
       b.setCredentialsExpired(false);
       b.setEnabled(true);
       b.setPassword("123");
       userRepository.save(b);
       a.setUser(b);
       authorityRepository.save(a);
       List<Authority> testAuthority = authorityRepository.findAuthoritiesByUserId(a.getUser().getId());
       assertNotNull(testAuthority);
       assertEquals(a.getUser().getId(),testAuthority.get(0).getUser().getId());
   }
}
