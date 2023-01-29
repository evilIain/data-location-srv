package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.in.CreateUserRequest;
import org.example.dto.in.GetUserLocationsByTimeRequest;
import org.example.dto.in.RegisterLocationRequest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.*;
import org.example.service.UserDataProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0/user")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataProcessingService userDataProcessingService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userDataProcessingService.createUser(createUserRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}")
    public UpdateUserResponse updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest,
                                         @PathVariable UUID userId) {
        updateUserRequest.setUserId(userId);
        return userDataProcessingService.updateUser(updateUserRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/location")
    public RegisterLocationResponse registerLocation(@RequestBody RegisterLocationRequest registerLocationRequest) {
        return userDataProcessingService.registerLocation(registerLocationRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/location")
    public GetUserLocationResponse getUserLocation(@PathVariable UUID userId) {
        return userDataProcessingService.getUserLocation(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{userId}/locations")
    public GetUserLocationsByTimeResponse getUserLocationsByTime(
            @PathVariable UUID userId,
            @Valid @RequestBody GetUserLocationsByTimeRequest getUserLocationsByTimeRequest) {
        getUserLocationsByTimeRequest.setUserId(userId);
        return userDataProcessingService.getUserLocationsByTime(getUserLocationsByTimeRequest);
    }

}
