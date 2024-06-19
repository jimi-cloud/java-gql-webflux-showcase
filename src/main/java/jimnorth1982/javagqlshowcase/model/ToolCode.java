package jimnorth1982.javagqlshowcase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ToolCode {
    @Id
    private Long id;
    private String code;
}
