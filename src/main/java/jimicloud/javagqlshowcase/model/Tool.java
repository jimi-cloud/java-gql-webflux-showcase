package jimicloud.javagqlshowcase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Tool {
    @Id
    private Long id;
    private Long brandId;
    private Long toolTypeId;
    private Long toolCodeId;
}