package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.*;
import jimnorth1982.javagqlshowcase.calendar.DateRange;
import jimnorth1982.javagqlshowcase.calendar.Holiday;
import jimnorth1982.javagqlshowcase.strategy.ChargeStrategy;
import jimnorth1982.javagqlshowcase.strategy.ChargeWeekdays;
import jimnorth1982.javagqlshowcase.strategy.ChargeWeekends;
import jimnorth1982.javagqlshowcase.strategy.NoChargeHolidays;
import jimnorth1982.javagqlshowcase.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private BigDecimal dailyRentalCharge;

    public RentalAgreement(Tool tool, RentalAgreementInput rentalAgreementInput) {
        this.tool = tool;
        this.discount = rentalAgreementInput.discount();
        this.checkOutDate = rentalAgreementInput.checkOutDate();
        this.rentalDays = rentalAgreementInput.rentalDays();
        this.dailyRentalCharge = tool.getToolType().getDailyRentalCharge();
    }

    public RentalAgreement validate() throws ValidationException {
        List<String> errorMessages = new ArrayList<>();
        if (this.rentalDays < 1) {
            errorMessages.add("Rental days must be greater than zero.");
        }
        if (this.discount.floatValue() >= BigDecimal.ONE.floatValue()) {
            errorMessages.add("Discount must not be more than 100%.");
        }
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(String.join(" ", errorMessages));
        }
        return this;
    }

    public RentalAgreement finalizeAgreement(List<Holiday> holidays) {
        List<ChargeStrategy> chargeStrategies = new ArrayList<>();
        if (this.tool.getToolType().isChargeWeekdays()) {
            chargeStrategies.add(new ChargeWeekdays());
        }

        if (this.tool.getToolType().isChargeWeekends()) {
            chargeStrategies.add(new ChargeWeekends());
        }

        if (!this.tool.getToolType().isChargeHolidays()) {
            chargeStrategies.add(new NoChargeHolidays(holidays));
        }

        this.dueDate = checkOutDate.plusDays(rentalDays - 1);
        this.chargeDays = reduceToSum(chargeStrategies, BigDecimal.ONE).intValue();

        this.preDiscountCharge = reduceToSum(chargeStrategies, tool.getToolType().getDailyRentalCharge())
                .setScale(2, RoundingMode.HALF_UP);

        this.discountAmount = preDiscountCharge
                .multiply(BigDecimal.ONE.subtract(discount))
                .setScale(2, RoundingMode.HALF_UP);

        this.finalCharge = preDiscountCharge
                .subtract(this.discountAmount)
                .setScale(2, RoundingMode.HALF_UP);
        return this;
    }

    private BigDecimal reduceToSum(List<ChargeStrategy> chargeStrategies, BigDecimal tool) {
        return new DateRange(checkOutDate, rentalDays).stream()
                .map((date) -> chargeStrategies
                        .stream()
                        .map(invoiceStrategy -> invoiceStrategy.apply(tool, date))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
