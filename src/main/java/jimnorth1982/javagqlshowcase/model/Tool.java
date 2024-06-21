package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ToolCode toolCode;
    @ManyToOne
    private ToolType toolType;
    @ManyToOne
    private Brand brand;
}
