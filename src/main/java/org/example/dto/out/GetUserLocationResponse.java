package org.example.dto.out;

import lombok.Builder;
import org.example.dto.common.Location;

import java.util.UUID;

@Builder
public record GetUserLocationResponse(
        UUID userId,

        String email,

        String firstName,

        String secondName,

        Location location
) {
}
