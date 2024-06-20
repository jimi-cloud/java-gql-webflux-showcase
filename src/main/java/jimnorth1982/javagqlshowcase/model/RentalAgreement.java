package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.*;
import jimnorth1982.javagqlshowcase.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Tool tool;
    private BigDecimal discount;
    private LocalDate checkOutDate;
    private int rentalDays;
    private LocalDate dueDate;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement(Tool tool, RentalAgreementInput rentalAgreementInput) {
        this.tool = tool;
        this.discount = rentalAgreementInput.discount();
        this.checkOutDate = rentalAgreementInput.checkOutDate();
        this.rentalDays = rentalAgreementInput.rentalDays();
    }

    public RentalAgreement validate() throws ValidationException {
        List<String> errorMessages = new ArrayList<>();
        if (this.rentalDays < 1) {
            errorMessages.add("Rental days needs to be greater than zero.");
        } if (this.discount.floatValue() >= BigDecimal.ONE.floatValue()) {
            errorMessages.add("Discount cannot be more than 100%.");
        }
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(String.join(" ", errorMessages));
        }
        return this;
    }
}
