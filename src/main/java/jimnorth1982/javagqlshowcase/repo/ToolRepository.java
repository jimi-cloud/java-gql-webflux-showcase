package jimnorth1982.javagqlshowcase.repo;

import jimnorth1982.javagqlshowcase.model.Tool;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ToolRepository extends Repository<Tool, Integer> {
    Tool findById(Integer id);

    List<Tool> findAllByBrandId(Integer brand);

    List<Tool> findAllByToolTypeId(Integer toolTypeId);

    List<Tool> findAll();
}
