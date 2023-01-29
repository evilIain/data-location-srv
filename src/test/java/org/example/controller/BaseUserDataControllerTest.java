package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Application;
import org.example.BaseIntegrationTest;
import org.example.controller.db.TestUserRepository;
import org.example.converter.UserMapper;
import org.example.entity.LocationEntity;
import org.example.entity.UserEntity;
import org.example.repository.LocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles(BaseIntegrationTest.TEST_PROFILE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
public abstract class BaseUserDataControllerTest extends BaseIntegrationTest {


    @Autowired
    protected ObjectMapper jackson2ObjectMapper;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected TestUserRepository userRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Autowired
    protected UserMapper userMapper;

    protected UserEntity userEntity;

    protected UserEntity userWithoutLocationEntity;
    protected LocationEntity locationEntity;
    protected LocationEntity anotherLocationEntity;

    @BeforeEach
    public void init() {
        userEntity = UserEntity.builder()
                .email("testuser@gmail.com")
                .firstName("John")
                .secondName("Doe")
                .build();
        userRepository.save(userEntity);

        userWithoutLocationEntity = UserEntity.builder()
                .email("peterparker@gmail.com")
                .firstName("Peter")
                .secondName("Parker")
                .build();
        userRepository.save(userWithoutLocationEntity);

        locationEntity = LocationEntity.builder()
                .latitude(52.25374589345)
                .longitude(10.34653242346)
                .user(userEntity)
                .build();

        anotherLocationEntity = LocationEntity.builder()
                .latitude(87.25374589345)
                .longitude(45.34653242346)
                .user(userEntity)
                .build();

        locationRepository.save(anotherLocationEntity);
        locationRepository.save(locationEntity);
    }

    @AfterEach
    public void tearDown() {

        locationRepository.deleteAll();
        userRepository.deleteAll();
    }
}
