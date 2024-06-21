package jimicloud.javagqlshowcase.strategy;

import jimicloud.javagqlshowcase.Utils;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;

public class ChargeWeekdays implements ChargeStrategy {
    @Override
    public BigDecimal apply(@NonNull BigDecimal dailyCharge, @NonNull LocalDate localDate) {
        if (Utils.isWeekday(localDate)) {
            return dailyCharge;
        }
        return ZERO;
    }
}
