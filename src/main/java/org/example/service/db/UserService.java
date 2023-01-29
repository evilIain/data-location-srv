package org.example.service.db;

import org.example.entity.UserEntity;

import java.util.UUID;

public interface UserService {

    UserEntity create(UserEntity userEntity);

    UserEntity findById(UUID id);

    UserEntity updateUser(UserEntity userEntity);
}
