package org.example.service.db.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.db.UserService;
import org.springframework.stereotype.Service;

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
}
