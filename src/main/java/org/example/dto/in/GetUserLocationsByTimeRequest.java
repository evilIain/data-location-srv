package org.example.dto.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class GetUserLocationsByTimeRequest {

    private UUID userId;

    @NotNull
    private Instant dateFrom;

    private Instant dateTo = Instant.now();
}
