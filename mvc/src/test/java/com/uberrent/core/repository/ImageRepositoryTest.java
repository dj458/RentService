package com.uberrent.core.repository;

import com.uberrent.core.domain.Image;
import com.uberrent.core.domain.Payment;
import com.uberrent.core.domain.User;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImageRepositoryTest extends RepositoryTest {
    @Test
    @Transactional
    public void findByUsername(){
        User b =new User();
        b.setFirstName("feng");
        b.setUsername("fzheng1");
        b.setLastName("zheng");
        b.setAccountExpired(false);
        b.setEmail("fzheng8@vt.edu");
        b.setAccountLocker(false);
        b.setAccountExpired(false);
        b.setCredentialsExpired(false);
        b.setEnabled(true);
        b.setPassword("123");
        userRepository.save(b);

        Image i=new Image();
        i.setUser(b);
        i.setS3Key("IMG_1372 3-5c1b84d1-a3a8-4e9c-896e-3474e1e69b25.JPG");
        i.setUrl("https://s3.us-east-2.amazonaws.com/rent-service-dev/IMG_1372 3-5c1b84d1-a3a8-4e9c-896e-3474e1e69b25.JPG");
        imageRepository.save(i);
        Optional<Image> imageTes=imageRepository.findByImageId(i.getId());
        assertNotNull(imageTes);
        assertEquals(i.getId(),imageTes.get().getId());

    }
}
