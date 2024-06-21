package jimicloud.javagqlshowcase.strategy;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@FunctionalInterface
public interface ChargeStrategy {
    BigDecimal apply(@NonNull BigDecimal dailyCharge, @NonNull LocalDate localDate);
}
