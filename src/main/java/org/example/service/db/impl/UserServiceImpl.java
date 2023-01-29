package org.example.service.db.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.UserEntity;
import org.example.exception.type.NotFoundException;
import org.example.repository.UserRepository;
import org.example.service.db.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity userEntity) {
        final UserEntity dbUserEntity = userRepository.save(userEntity);

        log.info("User created: {}", dbUserEntity);

        return dbUserEntity;
    }

    @Override
    public UserEntity findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity currentUserEntity = findById(userEntity.getId());

        currentUserEntity.setEmail(userEntity.getEmail());
        currentUserEntity.setFirstName(userEntity.getFirstName());
        currentUserEntity.setSecondName(userEntity.getSecondName());

        UserEntity updatedUserEntity = userRepository.save(currentUserEntity);

        log.info("User updated: {}", updatedUserEntity);

        return updatedUserEntity;
    }
}
