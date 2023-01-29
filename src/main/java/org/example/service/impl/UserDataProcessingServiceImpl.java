package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.UserMapper;
import org.example.dto.in.CreateUserRequest;
import org.example.dto.in.GetUserLocationsByTimeRequest;
import org.example.dto.in.RegisterLocationRequest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.*;
import org.example.entity.LocationEntity;
import org.example.entity.UserEntity;
import org.example.service.UserDataProcessingService;
import org.example.service.db.LocationService;
import org.example.service.db.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataProcessingServiceImpl implements UserDataProcessingService {

    private final UserService userService;
    private final LocationService locationService;
    private final UserMapper userMapper;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        final UserEntity userEntity = userMapper.toUserEntity(request);

        final UserEntity dbUserEntity = userService.create(userEntity);

        return userMapper.toCreateUserResponse(dbUserEntity);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        UserEntity userEntity = userMapper.toUserEntity(request);
        userService.findById(request.getUserId());

        UserEntity dbUpdatedUserEntity = userService.updateUser(userEntity);

        return userMapper.toUpdateUserResponse(dbUpdatedUserEntity);
    }

    @Override
    public GetUserLocationResponse getUserLocation(UUID userId) {
        UserEntity userEntity = userService.findById(userId);

        LocationEntity locationEntity = userEntity.getLocations()
                .stream()
                .max(Comparator.comparing(LocationEntity::getCreatedWhen))
                .orElse(null);

        return userMapper.toUserLocationResponse(userEntity, locationEntity);
    }

    @Override
    public GetUserLocationsByTimeResponse getUserLocationsByTime(GetUserLocationsByTimeRequest request) {
        userService.findById(request.getUserId());

        List<LocationEntity> locations = locationService.findLocations(request.getUserId(), request.getDateFrom(), request.getDateTo());

        return userMapper.toUserLocationsByTimeResponse(request.getUserId(), locations);
    }

    @Override
    public RegisterLocationResponse registerLocation(RegisterLocationRequest request) {
        UserEntity userEntity = userService.findById(request.userId());

        LocationEntity locationEntity = userMapper.toLocationEntity(request.location(), request.createdOn());
        locationEntity.setUser(userEntity);

        LocationEntity dbLocationEntity = locationService.registerLocation(locationEntity);

        return userMapper.toRegisterLocationResponse(dbLocationEntity);
    }
}
