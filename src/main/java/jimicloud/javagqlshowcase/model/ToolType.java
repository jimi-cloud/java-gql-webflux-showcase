package jimicloud.javagqlshowcase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ToolType {
    @Id
    private Integer id;
    private String type;
    private BigDecimal dailyRentalCharge;
    private boolean chargeWeekdays;
    private boolean chargeWeekends;
    private boolean chargeHolidays;
}
