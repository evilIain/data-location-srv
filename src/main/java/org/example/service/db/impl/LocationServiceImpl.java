package org.example.service.db.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.LocationEntity;
import org.example.repository.LocationRepository;
import org.example.service.db.LocationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<LocationEntity> findLocations(UUID userId, Instant dateFrom, Instant dateTo) {

        return locationRepository.findAllByUserIdAndCreatedWhenBetween(userId, dateFrom, dateTo);
    }

    @Override
    public LocationEntity registerLocation(LocationEntity locationEntity) {
        final LocationEntity dbLocationEntity = locationRepository.save(locationEntity);

        log.info("User created: {}", dbLocationEntity);

        return dbLocationEntity;
    }
}
