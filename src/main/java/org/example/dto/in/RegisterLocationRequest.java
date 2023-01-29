package org.example.dto.in;

import lombok.Builder;
import org.example.dto.common.Location;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Builder
public record RegisterLocationRequest(
        UUID userId,

        @NotNull
        Instant createdOn,

        Location location
) {

}
