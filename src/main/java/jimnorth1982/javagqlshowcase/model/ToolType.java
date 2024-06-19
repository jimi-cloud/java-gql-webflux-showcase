package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ToolType {
    @Id
    private Long id;
    private String type;
    private BigDecimal dailyRentalCharge;
}
