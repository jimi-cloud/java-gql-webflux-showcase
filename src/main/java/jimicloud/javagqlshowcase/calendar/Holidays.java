package jimicloud.javagqlshowcase.calendar;

import java.time.MonthDay;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.JULY;
import static java.time.Month.SEPTEMBER;

public class Holidays {
    public static List<Holiday> defaultHolidays() {
        return List.of(new ConsistentMonthDay(MonthDay.of(JULY, 4)), new FirstDayOfMonth(MONDAY, SEPTEMBER));
    }
}
