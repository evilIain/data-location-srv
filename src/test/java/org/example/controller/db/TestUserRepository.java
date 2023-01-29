package org.example.controller.db;

import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TestUserRepository extends UserRepository {

    @Query(value = "SELECT d from UserEntity d JOIN FETCH d.locations WHERE d.id = :id ")
    UserEntity getByIdWithJoin(UUID id);
}
