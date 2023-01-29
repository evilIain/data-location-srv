package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.in.CreateUserRequest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.CreateUserResponse;
import org.example.dto.out.UpdateUserResponse;
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
}
