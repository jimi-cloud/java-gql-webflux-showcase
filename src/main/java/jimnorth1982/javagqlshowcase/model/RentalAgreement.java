package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalAgreement {
    @Id
    private Long id;
    @ManyToOne
    private Tool tool;
    private BigDecimal discount;
    private LocalDate checkOutDate;
    private int rentalDays;
}
