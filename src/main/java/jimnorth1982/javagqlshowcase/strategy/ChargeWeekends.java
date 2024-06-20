package jimnorth1982.javagqlshowcase.strategy;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;
import static jimnorth1982.javagqlshowcase.Utils.isWeekendDay;

public class ChargeWeekends implements ChargeStrategy {
    @Override
    public BigDecimal apply(@NonNull BigDecimal dailyCharge, @NonNull LocalDate localDate) {
        if (isWeekendDay(localDate)) {
            return dailyCharge;
        }
        return ZERO;
    }
}
