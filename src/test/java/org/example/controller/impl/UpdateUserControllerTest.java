package org.example.controller.impl;

import org.example.controller.BaseUserDataControllerTest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.UpdateUserResponse;
import org.example.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateUserControllerTest extends BaseUserDataControllerTest {

    @Test
    public void updateUser() {

        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .email("johndoe@gmail.com")
                .firstName("John")
                .secondName("Doe")
                .build();

        final ResponseEntity<UpdateUserResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user/" + userEntity.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        UpdateUserResponse response = responseEntity.getBody();
        assertNotNull(response);

        final UserEntity dbUserEntity = userRepository.findById(response.userId()).get();

        UpdateUserResponse expectedResponse = UpdateUserResponse.builder()
                .userId(dbUserEntity.getId())
                .email(dbUserEntity.getEmail())
                .firstName(dbUserEntity.getFirstName())
                .secondName(dbUserEntity.getSecondName())
                .build();

        assertEquals(expectedResponse, response);
    }
}
