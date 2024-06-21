package jimnorth1982.javagqlshowcase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import jimnorth1982.javagqlshowcase.model.Tool;
import jimnorth1982.javagqlshowcase.repo.ToolRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@DgsComponent
@AllArgsConstructor
public class ToolDataFetcher {
    private final ToolRepository toolRepository;

    @DgsQuery
    public List<Tool> findAllTools(DataFetchingEnvironment dfe) {
        return toolRepository.findAll();
    }

    @DgsQuery
    public Tool findToolById(DataFetchingEnvironment dfe, @InputArgument Integer id) {
        return toolRepository.findById(id);
    }

    @DgsQuery
    public List<Tool> findToolsByBrandId(DataFetchingEnvironment dfe, @InputArgument Integer brandId) {
        return toolRepository.findAllByBrandId(brandId);
    }

    @DgsQuery
    public List<Tool> findToolsByToolTypeId(DataFetchingEnvironment dfe, @InputArgument Integer toolTypeId) {
        return toolRepository.findAllByToolTypeId(toolTypeId);
    }
}