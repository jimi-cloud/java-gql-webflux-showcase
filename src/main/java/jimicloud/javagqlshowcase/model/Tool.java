package jimicloud.javagqlshowcase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Tool {
    @Id
    private Integer id;
    private String name;
    private Integer brandId;
    private Integer toolTypeId;
    private Integer toolCodeId;

    private Brand brand;
    private ToolType toolType;
    private ToolCode toolCode;

    public Tool(Integer id, String name, Integer brandId, Integer toolTypeId, Brand brandId1, ToolType toolTypeId1, ToolCode toolCodeId) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.toolTypeId = toolTypeId;
        this.brand = brandId1;
        this.toolType = toolTypeId1;
        this.toolCode = toolCodeId;
    }
}