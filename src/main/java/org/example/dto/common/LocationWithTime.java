package org.example.dto.common;

import lombok.Builder;

import java.time.Instant;

@Builder
public record LocationWithTime(
        Instant createdOn,

        Location location
) {
}
