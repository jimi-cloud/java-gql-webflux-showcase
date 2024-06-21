package jimicloud.javagqlshowcase.repo;

import jimicloud.javagqlshowcase.model.RentalAgreement;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RentalAgreementRepository extends ReactiveCrudRepository<RentalAgreement, Long> {
    @NotNull
    Flux<RentalAgreement> findAll();

    @NotNull
    Mono<RentalAgreement> findById(@NotNull Long id);
}
