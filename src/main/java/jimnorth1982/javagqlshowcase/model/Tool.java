package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Tool {
    @Id
    private Long id;
    @ManyToOne
    private ToolCode toolCode;
    @ManyToOne
    private ToolType toolType;
    @ManyToOne
    private Brand brand;
}
