package jimicloud.javagqlshowcase.repo;

import jimicloud.javagqlshowcase.model.ToolCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ToolCodeRepository extends ReactiveCrudRepository<ToolCode, Integer> {
    @NotNull
    Mono<ToolCode> findById(@NotNull Integer id);

    @NotNull
    Flux<ToolCode> findAll();
}