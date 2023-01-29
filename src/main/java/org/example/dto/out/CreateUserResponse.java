package org.example.dto.out;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserResponse(
        UUID userId,

        String email,

        String firstName,

        String secondName
) {
}
