package org.example.controller.impl;

import org.example.controller.BaseUserDataControllerTest;
import org.example.dto.common.Location;
import org.example.dto.out.GetUserLocationResponse;
import org.example.entity.LocationEntity;
import org.example.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetUserLocationControllerTest extends BaseUserDataControllerTest {

    @Test
    public void getUserLocation() {

        final ResponseEntity<GetUserLocationResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user/" + userEntity.getId() + "/location",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        GetUserLocationResponse response = responseEntity.getBody();
        assertNotNull(response);

        final UserEntity dbUserEntity = userRepository.getByIdWithJoin(response.userId());

        LocationEntity latestLocationEntity =  dbUserEntity.getLocations()
                .stream()
                .max(Comparator.comparing(LocationEntity::getCreatedWhen))
                .get();

        GetUserLocationResponse expectedResponse = GetUserLocationResponse.builder()
                .userId(dbUserEntity.getId())
                .email(dbUserEntity.getEmail())
                .firstName(dbUserEntity.getFirstName())
                .secondName(dbUserEntity.getSecondName())
                .location(new Location(latestLocationEntity.getLatitude(), latestLocationEntity.getLongitude()))
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getUserLocationButNoLocationFound() {

        final ResponseEntity<GetUserLocationResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user/" + userWithoutLocationEntity.getId() + "/location",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        GetUserLocationResponse response = responseEntity.getBody();
        assertNotNull(response);

        final UserEntity dbUserEntity = userRepository.findById(response.userId()).get();

        GetUserLocationResponse expectedResponse = GetUserLocationResponse.builder()
                .userId(dbUserEntity.getId())
                .email(dbUserEntity.getEmail())
                .firstName(dbUserEntity.getFirstName())
                .secondName(dbUserEntity.getSecondName())
                .location(null)
                .build();

        assertEquals(expectedResponse, response);
    }
}
