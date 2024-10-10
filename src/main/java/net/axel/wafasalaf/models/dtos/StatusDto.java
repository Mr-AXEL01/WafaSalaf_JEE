package net.axel.wafasalaf.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record StatusDto(
        @NotBlank String name
) {
}
