package com.uberrent.core.repository;

import com.uberrent.core.domain.User;
import com.uberrent.core.extend.security.JwtTokenUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.LiteDevice;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

public class GetUsernameFromTokenTest extends RepositoryTest {
    @Autowired
    public JwtTokenUtil jwtTokenUtil;
    @Test
    @Transactional
    public void getUsernameFromTokenTest() {
        User u = new User();
        u.setFirstName("Zheng");
        u.setUsername("fzheng8");
        u.setLastName("Feng");
        u.setAccountExpired(false);
        u.setEmail("fzheng8");
        u.setAccountLocker(false);
        u.setAccountExpired(false);
        u.setCredentialsExpired(false);
        u.setEnabled(true);
        u.setPassword("123");
        userRepository.save(u);

        String token = jwtTokenUtil.generateToken(u, LiteDevice.MOBILE_INSTANCE);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        assertEquals(u.getUsername(),username);
    }
}
