package net.axel.wafasalaf.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.axel.wafasalaf.models.enums.Civility;

import java.time.LocalDate;

public record RequestDto(
        @NotBlank String project,
        @NotBlank String work,
        @NotNull double amountLoan,
        @NotNull int duration,
        @NotNull double monthly,
        @NotBlank @Email String email,
        @NotBlank  String phone,
        @NotNull Civility civility,
        @NotBlank String lastName,
        @NotBlank String firstName,
        @NotBlank String cin,
        @NotNull LocalDate birthDate,
        @NotNull LocalDate hiringDate,
        @NotNull double income,
        @NotNull boolean haveCredit
) {
}
