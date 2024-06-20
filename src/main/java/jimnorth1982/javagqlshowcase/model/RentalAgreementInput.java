package jimnorth1982.javagqlshowcase.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RentalAgreementInput(Long toolId, BigDecimal discount, LocalDate checkOutDate, int rentalDays) {
}
