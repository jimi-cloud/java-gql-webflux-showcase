package jimicloud.javagqlshowcase.repo;

import jimicloud.javagqlshowcase.model.Brand;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrandRepository extends ReactiveCrudRepository<Brand, Long> {
    @NotNull
    Mono<Brand> findById(@NotNull Long id);

    @NotNull
    Flux<Brand> findAll();
}
