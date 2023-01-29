package org.example.converter;

import org.example.dto.in.CreateUserRequest;
import org.example.dto.out.CreateUserResponse;
import org.example.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toUserEntity(CreateUserRequest createUserRequest);

    @Mapping(source = "id", target = "userId")
    CreateUserResponse toCreateUserResponse(UserEntity userEntity);
}
