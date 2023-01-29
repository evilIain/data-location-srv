package org.example.dto.out;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdateUserResponse(
        UUID userId,

        String email,

        String firstName,

        String secondName
) {
}
