package jimicloud.javagqlshowcase.model;

import jimicloud.javagqlshowcase.calendar.DateRange;
import jimicloud.javagqlshowcase.calendar.Holiday;
import jimicloud.javagqlshowcase.strategy.ChargeStrategy;
import jimicloud.javagqlshowcase.strategy.ChargeWeekdays;
import jimicloud.javagqlshowcase.strategy.ChargeWeekends;
import jimicloud.javagqlshowcase.strategy.NoChargeHolidays;
import jimicloud.javagqlshowcase.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalAgreement {

    @Id
    private Long id;
    private Long toolId;
    private BigDecimal discount;
    private LocalDate checkOutDate;
    private int rentalDays;
    private LocalDate dueDate;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    private BigDecimal dailyRentalCharge;

    public RentalAgreement(RentalAgreementInput rentalAgreementInput) {
        this.toolId = rentalAgreementInput.toolId();
        this.discount = rentalAgreementInput.discount();
        this.checkOutDate = rentalAgreementInput.checkOutDate();
        this.rentalDays = rentalAgreementInput.rentalDays();
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

    public RentalAgreement finalizeAgreement(ToolType toolType, List<Holiday> holidays) {
        List<ChargeStrategy> chargeStrategies = new ArrayList<>();
        if (toolType.isChargeWeekdays()) {
            chargeStrategies.add(new ChargeWeekdays());
        }

        if (toolType.isChargeWeekends()) {
            chargeStrategies.add(new ChargeWeekends());
        }

        if (!toolType.isChargeHolidays()) {
            chargeStrategies.add(new NoChargeHolidays(holidays));
        }
        this.dailyRentalCharge = toolType.getDailyRentalCharge();
        this.dueDate = checkOutDate.plusDays(rentalDays - 1);
        this.chargeDays = reduceToSum(chargeStrategies, BigDecimal.ONE).intValue();

        this.preDiscountCharge = reduceToSum(chargeStrategies, toolType.getDailyRentalCharge())
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
