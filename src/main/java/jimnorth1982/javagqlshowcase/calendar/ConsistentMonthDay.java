package jimnorth1982.javagqlshowcase.calendar;

import jimnorth1982.javagqlshowcase.Utils;
import lombok.Data;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

@Data
public class ConsistentMonthDay implements Holiday {
    private MonthDay monthDay;

    public ConsistentMonthDay(MonthDay monthDay) {
        this.monthDay = monthDay;
    }

    @Override
    public LocalDate forYear(Year year) {
        return calculateHolidayForWeekendDate(LocalDate.of(year.getValue(), monthDay.getMonth(), monthDay.getDayOfMonth()));
    }

    static LocalDate calculateHolidayForWeekendDate(LocalDate localDate) {
        if (Utils.isSaturday(localDate)) {
            return localDate.minusDays(1);
        } else if (Utils.isSunday(localDate)) {
            return localDate.plusDays(1);
        } else {
            return localDate;

        }
    }
}
