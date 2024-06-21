package jimicloud.javagqlshowcase.strategy;

import jimicloud.javagqlshowcase.Utils;
import jimicloud.javagqlshowcase.calendar.Holiday;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class NoChargeHolidays implements ChargeStrategy {
    private final List<Holiday> holidays;

    public NoChargeHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    @Override
    public BigDecimal apply(@NonNull BigDecimal dailyCharge, @NonNull LocalDate localDate) {
        if (Utils.isHoliday(localDate, this.holidays)) {
            return ZERO.subtract(dailyCharge);
        }
        return ZERO;
    }
}
