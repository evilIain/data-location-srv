package org.example.controller.impl;

import org.example.controller.BaseUserDataControllerTest;
import org.example.dto.common.Location;
import org.example.dto.common.LocationWithTime;
import org.example.dto.in.GetUserLocationsByTimeRequest;
import org.example.dto.out.GetUserLocationsByTimeResponse;
import org.example.entity.UserEntity;
import org.example.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetUserLocationsByTimeControllerTest extends BaseUserDataControllerTest {

    @Test
    public void getUserLocationsByTime() {

        GetUserLocationsByTimeRequest request = GetUserLocationsByTimeRequest.builder()
                .dateFrom(Instant.now().minus(3, ChronoUnit.HOURS))
                .dateTo(Instant.now())
                .build();

        final ResponseEntity<GetUserLocationsByTimeResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user/" + userEntity.getId() + "/locations",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        GetUserLocationsByTimeResponse response = responseEntity.getBody();
        assertNotNull(response);

        final UserEntity dbUserEntity = userRepository.getByIdWithJoin(response.userId());

        List<LocationWithTime> locations = dbUserEntity.getLocations().stream()
                .filter(location -> location.getCreatedWhen().isAfter(request.getDateFrom())
                        && location.getCreatedWhen().isBefore(request.getDateTo()))
                .map(location -> LocationWithTime.builder()
                        .createdOn(location.getCreatedWhen())
                        .location(new Location(location.getLatitude(), location.getLongitude()))
                        .build())
                .toList();

        GetUserLocationsByTimeResponse expectedResponse = GetUserLocationsByTimeResponse.builder()
                .userId(dbUserEntity.getId())
                .locations(locations)
                .build();

        assertEquals(2, locations.size());
        assertEquals(expectedResponse, response);

    }

    @Test
    public void getUserLocationsByTimeButDateFromIsNull() {

        GetUserLocationsByTimeRequest request = GetUserLocationsByTimeRequest.builder()
                .build();

        final ResponseEntity<ErrorResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/user/" + userEntity.getId() + "/locations",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);

        assertEquals("Validation Error", errorResponse.message());

        assertTrue(errorResponse.errors().get(0).message().startsWith("dateFrom: "));

    }
}
