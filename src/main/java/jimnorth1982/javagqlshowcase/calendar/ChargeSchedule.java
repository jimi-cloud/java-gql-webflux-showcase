package jimnorth1982.javagqlshowcase.calendar;


import jimnorth1982.javagqlshowcase.strategy.ChargeStrategy;

import java.util.List;

public record ChargeSchedule(List<ChargeStrategy> chargeStrategies) {
}
