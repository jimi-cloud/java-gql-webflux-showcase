package jimnorth1982.javagqlshowcase.strategy;

import jimnorth1982.javagqlshowcase.calendar.Holiday;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static jimnorth1982.javagqlshowcase.Utils.isHoliday;

public class NoChargeHolidays implements ChargeStrategy {
    private final List<Holiday> holidays;

    public NoChargeHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    @Override
    public BigDecimal apply(@NonNull BigDecimal dailyCharge, @NonNull LocalDate localDate) {
        if (isHoliday(localDate, this.holidays)) {
            return ZERO.subtract(dailyCharge);
        }
        return ZERO;
    }
}
