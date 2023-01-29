package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.UserMapper;
import org.example.dto.in.CreateUserRequest;
import org.example.dto.out.CreateUserResponse;
import org.example.entity.UserEntity;
import org.example.service.UserDataProcessingService;
import org.example.service.db.UserService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataProcessingServiceImpl implements UserDataProcessingService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        final UserEntity userEntity = userMapper.toUserEntity(request);

        final UserEntity dbUserEntity = userService.create(userEntity);

        return userMapper.toCreateUserResponse(dbUserEntity);
    }
}
