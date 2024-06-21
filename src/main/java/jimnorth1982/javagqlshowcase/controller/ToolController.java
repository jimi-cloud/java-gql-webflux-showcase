package jimnorth1982.javagqlshowcase.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jimnorth1982.javagqlshowcase.model.Tool;
import jimnorth1982.javagqlshowcase.repo.ToolRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@DgsComponent
public class ToolController {
    private final ToolRepository toolRepository;

    @DgsQuery
    public Tool findToolById(@InputArgument Integer id) {
        return toolRepository.findById(id);
    }

    @DgsQuery
    public List<Tool> findAllTools() {
        return toolRepository.findAll();
    }

    @DgsQuery
    public List<Tool> findToolsByBrandId(@InputArgument Integer brandId) {
        return toolRepository.findAllByBrandId(brandId);
    }

    @DgsQuery
    public List<Tool> findToolsByToolTypeId(@InputArgument Integer toolTypeId) {
        return toolRepository.findAllByToolTypeId(toolTypeId);
    }
}
