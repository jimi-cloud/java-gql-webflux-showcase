package jimicloud.javagqlshowcase.repo;

import jimicloud.javagqlshowcase.model.ToolCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ToolCodeRepository extends ReactiveCrudRepository<ToolCode, Long> {
    @NotNull
    Mono<ToolCode> findById(@NotNull Long id);

    @NotNull
    Flux<ToolCode> findAll();
}