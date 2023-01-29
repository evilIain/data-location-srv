package org.example.converter;

import org.example.dto.common.Location;
import org.example.dto.common.LocationWithTime;
import org.example.dto.in.CreateUserRequest;
import org.example.dto.in.UpdateUserRequest;
import org.example.dto.out.*;
import org.example.entity.LocationEntity;
import org.example.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toUserEntity(CreateUserRequest createUserRequest);

    @Mapping(source = "userId", target = "id")
    UserEntity toUserEntity(UpdateUserRequest updateUserRequest);

    @Mapping(source = "id", target = "userId")
    CreateUserResponse toCreateUserResponse(UserEntity userEntity);

    @Mapping(source = "id", target = "userId")
    UpdateUserResponse toUpdateUserResponse(UserEntity userEntity);

    @Mappings({
            @Mapping(source = "userEntity.id", target = "userId"),
            @Mapping(source = "userEntity.email", target = "email"),
            @Mapping(source = "userEntity.firstName", target = "firstName"),
            @Mapping(source = "userEntity.secondName", target = "secondName"),
            @Mapping(source = "locationEntity", target = "location")
    })
    GetUserLocationResponse toUserLocationResponse(UserEntity userEntity, LocationEntity locationEntity);

    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "locations", target = "locations")
    })
    GetUserLocationsByTimeResponse toUserLocationsByTimeResponse(UUID userId, List<LocationEntity> locations);

    @Mappings({
            @Mapping(source = "createdWhen", target = "createdOn"),
            @Mapping(source = "latitude", target = "location.latitude"),
            @Mapping(source = "longitude", target = "location.longitude"),
    })
    LocationWithTime toLocationWithTime(LocationEntity location);

    @Mapping(source = "createdWhen", target = "createdWhen")
    LocationEntity toLocationEntity(Location location, Instant createdWhen);

    @Mappings({
            @Mapping(source = "id", target = "locationId"),
            @Mapping(source = "createdWhen", target = "createdOn")
    })
    RegisterLocationResponse toRegisterLocationResponse(LocationEntity locationEntity);
}
