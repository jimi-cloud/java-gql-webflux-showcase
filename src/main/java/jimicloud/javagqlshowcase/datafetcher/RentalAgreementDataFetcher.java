package jimicloud.javagqlshowcase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jimicloud.javagqlshowcase.model.RentalAgreement;
import jimicloud.javagqlshowcase.model.RentalAgreementInput;
import jimicloud.javagqlshowcase.repo.RentalAgreementRepository;
import jimicloud.javagqlshowcase.repo.ToolRepository;
import jimicloud.javagqlshowcase.repo.ToolTypeRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

import static jimicloud.javagqlshowcase.calendar.Holidays.defaultHolidays;

@DgsComponent
@AllArgsConstructor
public class RentalAgreementDataFetcher {
    private final RentalAgreementRepository rentalAgreementRepository;
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;

    @DgsQuery
    public Mono<RentalAgreement> findRentalAgreementById(@InputArgument Integer id) {
        return rentalAgreementRepository.findById(id.longValue());
    }

    @DgsQuery
    public Flux<RentalAgreement> findAllRentalAgreements() {
        return rentalAgreementRepository.findAll();
    }

    @DgsMutation
    public Mono<RentalAgreement> createRentalAgreement(@InputArgument RentalAgreementInput rentalAgreementInput) {
        return toolRepository.findById(rentalAgreementInput.toolId())
                .publishOn(Schedulers.boundedElastic())
                .map(tool -> new RentalAgreement(rentalAgreementInput)
                        .validate()
                        .finalizeAgreement(
                                Objects.requireNonNull(toolTypeRepository.findById(tool.getToolTypeId()).block()),
                                defaultHolidays()
                        )
                )
                .flatMap(rentalAgreementRepository::save);
    }
}