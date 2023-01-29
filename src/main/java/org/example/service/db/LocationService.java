package org.example.service.db;

import org.example.entity.LocationEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface LocationService {

    List<LocationEntity> findLocations(UUID userId, Instant dateFrom, Instant dateTo);

    LocationEntity registerLocation(LocationEntity locationEntity);
}
