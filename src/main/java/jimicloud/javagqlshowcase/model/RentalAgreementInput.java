package jimicloud.javagqlshowcase.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public final class RentalAgreementInput {
    private final Long toolId;
    private final BigDecimal discount;
    private final LocalDate checkOutDate;
    private final Integer rentalDays;

    public RentalAgreementInput() {
        this.toolId = 0L;
        this.discount = BigDecimal.ZERO;
        this.checkOutDate = LocalDate.now();
        this.rentalDays = 0;
    }

    public RentalAgreementInput(Map<String, Object> map) {
        this.toolId = (Long) map.get("toolId");
        this.discount = BigDecimal.valueOf(Double.parseDouble(map.get("discount").toString()));
        this.checkOutDate = LocalDate.parse(map.get("checkOutDate").toString(), DateTimeFormatter.ISO_DATE);
        this.rentalDays = (Integer) map.get("rentalDays");
    }

    // getters and setters...

    public Long toolId() {
        return toolId;
    }

    public BigDecimal discount() {
        return discount;
    }

    public LocalDate checkOutDate() {
        return checkOutDate;
    }

    public Integer rentalDays() {
        return rentalDays;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RentalAgreementInput) obj;
        return Objects.equals(this.toolId, that.toolId) &&
                Objects.equals(this.discount, that.discount) &&
                Objects.equals(this.checkOutDate, that.checkOutDate) &&
                Objects.equals(this.rentalDays, that.rentalDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolId, discount, checkOutDate, rentalDays);
    }

    @Override
    public String toString() {
        return "RentalAgreementInput[" +
                "toolId=" + toolId + ", " +
                "discount=" + discount + ", " +
                "checkOutDate=" + checkOutDate + ", " +
                "rentalDays=" + rentalDays + ']';
    }

}
