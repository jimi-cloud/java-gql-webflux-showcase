package jimnorth1982.javagqlshowcase.repo;

import jimnorth1982.javagqlshowcase.model.Tool;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ToolRepository extends Repository<Tool, Long> {
    Tool findById(Long id);

    List<Tool> findAllByBrandId(Long brand);

    List<Tool> findAllByToolTypeId(Long toolTypeId);

    List<Tool> findAll();
}
