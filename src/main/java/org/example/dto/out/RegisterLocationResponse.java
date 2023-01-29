package org.example.dto.out;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record RegisterLocationResponse(
        UUID locationId,

        Double latitude,

        Double longitude,

        Instant createdOn
) {
}
