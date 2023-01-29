package org.example.controller.impl;

import org.example.controller.BaseUserDataControllerTest;
import org.example.dto.common.Location;
import org.example.dto.in.RegisterLocationRequest;
import org.example.dto.out.RegisterLocationResponse;
import org.example.entity.LocationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegisterLocationControllerTest extends BaseUserDataControllerTest {

    @Test
    public void registerLocation() {
        final RegisterLocationRequest registerLocationRequest = RegisterLocationRequest.builder()
                .userId(userEntity.getId())
                .createdOn(Instant.now())
                .location(new Location(12.34565678, 51.345734534))
                .build();

        final ResponseEntity<RegisterLocationResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user/location",
                HttpMethod.POST,
                new HttpEntity<>(registerLocationRequest),
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        RegisterLocationResponse response = responseEntity.getBody();
        assertNotNull(response);

        final LocationEntity dbLocationEntity = locationRepository.findById(response.locationId()).get();

        RegisterLocationResponse expectedResponse = RegisterLocationResponse.builder()
                .locationId(dbLocationEntity.getId())
                .longitude(dbLocationEntity.getLongitude())
                .latitude(dbLocationEntity.getLatitude())
                .createdOn(dbLocationEntity.getCreatedWhen())
                .build();

        assertEquals(expectedResponse.locationId(), response.locationId());
        assertEquals(expectedResponse.latitude(), response.latitude());
        assertEquals(expectedResponse.longitude(), response.longitude());
    }
}
