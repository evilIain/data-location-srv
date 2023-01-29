package org.example.controller.impl;

import org.example.controller.BaseUserDataControllerTest;
import org.example.dto.in.CreateUserRequest;
import org.example.dto.out.CreateUserResponse;
import org.example.entity.UserEntity;
import org.example.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserControllerTest extends BaseUserDataControllerTest {

    @Test
    public void createUser() {

        final CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .email("testuser1@gmail.com")
                .firstName("Peter")
                .secondName("Parker")
                .build();

        final ResponseEntity<CreateUserResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user",
                HttpMethod.POST,
                new HttpEntity<>(createUserRequest),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        CreateUserResponse response = responseEntity.getBody();
        assertNotNull(response);

        final UserEntity dbUserEntity = userRepository.findById(response.userId()).get();

        CreateUserResponse expectedResponse = CreateUserResponse.builder()
                .userId(dbUserEntity.getId())
                .email(dbUserEntity.getEmail())
                .firstName(dbUserEntity.getFirstName())
                .secondName(dbUserEntity.getSecondName())
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    public void createUserButEmailNotValid() {

        final CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .email("testuser1")
                .firstName("Peter")
                .secondName("Parker")
                .build();

        final ResponseEntity<ErrorResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user",
                HttpMethod.POST,
                new HttpEntity<>(createUserRequest),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);

        assertEquals("Validation Error", errorResponse.message());

        assertTrue(errorResponse.errors().get(0).message().startsWith("email: Email is not valid"));
    }
}
