package jimnorth1982.javagqlshowcase.calendar;

import java.time.LocalDate;
import java.time.Year;

public interface Holiday {
    LocalDate forYear(Year year);
}
