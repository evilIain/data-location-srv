package org.example.repository;

import org.example.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {

    List<LocationEntity> findAllByUserIdAndCreatedWhenBetween(UUID userId, Instant createdWhenFrom, Instant createdWhenTo);
}
