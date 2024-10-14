package net.axel.wafasalaf.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestStatusDto(
        @NotNull UUID requestId,
        @NotNull UUID statusId,
        @NotBlank String description
) {
}
