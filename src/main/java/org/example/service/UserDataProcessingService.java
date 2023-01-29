package org.example.service;

import org.example.dto.in.CreateUserRequest;
import org.example.dto.in.GetUserLocationsByTimeRequest;
import org.example.dto.in.RegisterLocationRequest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.*;

import java.util.UUID;

public interface UserDataProcessingService {

    CreateUserResponse createUser(CreateUserRequest request);

    UpdateUserResponse updateUser(UpdateUserRequest request);

    GetUserLocationResponse getUserLocation(UUID userId);

    GetUserLocationsByTimeResponse getUserLocationsByTime(GetUserLocationsByTimeRequest request);

    RegisterLocationResponse registerLocation(RegisterLocationRequest request);
}
