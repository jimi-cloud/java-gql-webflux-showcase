package jimnorth1982.javagqlshowcase.repo;

import jimnorth1982.javagqlshowcase.model.Brand;
import jimnorth1982.javagqlshowcase.model.Tool;
import jimnorth1982.javagqlshowcase.model.ToolType;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ToolRepository extends Repository<Tool, Long> {
    Tool findById(Long id);

    List<Tool> getAllByBrand(Brand brand);

    List<Tool> getAllByToolType(ToolType toolType);
}
