package org.example.dto.in;

import lombok.Builder;
import org.example.dto.common.Location;

import java.time.Instant;
import java.util.UUID;

@Builder
public record RegisterLocationRequest(
        UUID userId,

        Instant createdOn,

        Location location
) {

}
