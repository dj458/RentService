package com.uberrent.core.repository;

import com.uberrent.web.config.AppConfig;
import com.uberrent.core.repository.AuthorityRepository;
import com.uberrent.core.repository.EquipmentRepository;
import com.uberrent.core.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class RepositoryTest {
    @Autowired
    protected EquipmentRepository equipmentRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected AuthorityRepository authorityRepository;
}
