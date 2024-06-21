package jimicloud.javagqlshowcase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jimicloud.javagqlshowcase.model.Tool;
import jimicloud.javagqlshowcase.repo.ToolRepository;
import jimicloud.javagqlshowcase.repo.ToolTypeRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DgsComponent
@AllArgsConstructor
public class ToolDataFetcher {
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;

    @DgsQuery
    public Flux<Tool> findAllTools() {
        return toolRepository.findAll();
    }

    @DgsQuery
    public Mono<Tool> findToolById(@InputArgument Integer id) {
        return toolRepository.findById(id);
    }

    @DgsQuery
    public Flux<Tool> findToolsByBrandId(@InputArgument Integer brandId) {
        return toolRepository.findAllByBrandId(brandId);
    }

    @DgsQuery
    public Flux<Tool> findToolsByToolTypeId(@InputArgument Integer toolTypeId) {
        return toolRepository.findAllByToolTypeId(toolTypeId);
    }
}