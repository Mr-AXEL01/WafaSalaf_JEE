package net.axel.wafasalaf.models.dtos;

import net.axel.wafasalaf.models.enums.Civility;

import java.time.LocalDate;

public record RequestDto(
        String project,
        String work,
        double amountLoan,
        int duration,
        double monthly,
        String phone,
        Civility civility,
        String lastName,
        String firstName,
        LocalDate birthday,
        LocalDate hiringDay,
        double income,
        boolean haveCredit
) {
}
