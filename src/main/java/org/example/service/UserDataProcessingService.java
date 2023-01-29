package org.example.service;

import org.example.dto.in.CreateUserRequest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.CreateUserResponse;
import org.example.dto.out.UpdateUserResponse;

public interface UserDataProcessingService {

    CreateUserResponse createUser(CreateUserRequest request);

    UpdateUserResponse updateUser(UpdateUserRequest request);
}
