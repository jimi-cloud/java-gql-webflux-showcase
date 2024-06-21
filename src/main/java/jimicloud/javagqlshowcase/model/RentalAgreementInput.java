package jimicloud.javagqlshowcase.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RentalAgreementInput(Integer toolId, BigDecimal discount, LocalDate checkOutDate, Integer rentalDays) {
}
