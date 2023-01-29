package org.example.service;

import org.example.dto.in.CreateUserRequest;
import org.example.dto.out.CreateUserResponse;

public interface UserDataProcessingService {

    CreateUserResponse createUser(CreateUserRequest request);
}
