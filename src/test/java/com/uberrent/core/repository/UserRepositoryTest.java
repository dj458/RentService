package com.uberrent.core.repository;

import com.uberrent.core.domain.User;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRepositoryTest extends RepositoryTest {
    @Test
    @Transactional
    public void findByUsernameTest(){
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
        Optional<User> testFirstname = userRepository.findByUsername(b.getUsername());

        assertNotNull(testFirstname);
        assertEquals(b.getFirstName(),testFirstname.get().getFirstName());
    }
}
