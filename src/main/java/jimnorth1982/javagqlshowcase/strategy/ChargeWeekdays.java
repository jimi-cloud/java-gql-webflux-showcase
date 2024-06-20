package jimnorth1982.javagqlshowcase.strategy;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;
import static jimnorth1982.javagqlshowcase.Utils.isWeekday;

public class ChargeWeekdays implements ChargeStrategy {
    @Override
    public BigDecimal apply(@NonNull BigDecimal dailyCharge, @NonNull LocalDate localDate) {
        if (isWeekday(localDate)) {
            return dailyCharge;
        }
        return ZERO;
    }
}
