package jimicloud.javagqlshowcase.repo;

import jimicloud.javagqlshowcase.model.ToolType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ToolTypeRepository extends ReactiveCrudRepository<ToolType, Integer> {
    @NotNull
    Mono<ToolType> findById(@NotNull Integer id);

    @NotNull
    Flux<ToolType> findAll();
}