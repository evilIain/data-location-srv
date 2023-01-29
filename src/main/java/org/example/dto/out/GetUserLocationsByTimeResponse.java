package org.example.dto.out;

import lombok.Builder;
import org.example.dto.common.LocationWithTime;

import java.util.List;
import java.util.UUID;

@Builder
public record GetUserLocationsByTimeResponse(
        UUID userId,

        List<LocationWithTime> locations
) {
}
