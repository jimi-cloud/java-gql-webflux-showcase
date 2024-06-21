package jimicloud.javagqlshowcase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jimicloud.javagqlshowcase.model.RentalAgreement;
import jimicloud.javagqlshowcase.model.RentalAgreementInput;
import jimicloud.javagqlshowcase.model.Tool;
import jimicloud.javagqlshowcase.repo.RentalAgreementRepository;
import jimicloud.javagqlshowcase.repo.ToolRepository;
import jimicloud.javagqlshowcase.validation.ValidationException;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static jimicloud.javagqlshowcase.calendar.Holidays.defaultHolidays;

@DgsComponent
@AllArgsConstructor
public class RentalAgreementDataFetcher {
    private final RentalAgreementRepository rentalAgreementRepository;
    private final ToolRepository toolRepository;

    @DgsQuery
    public Mono<RentalAgreement> findRentalAgreementById(@InputArgument Integer id) {
        return rentalAgreementRepository.findById(id);
    }

    @DgsQuery
    public Flux<RentalAgreement> findAllRentalAgreements() {
        return rentalAgreementRepository.findAll();
    }

    @DgsMutation
    public Mono<RentalAgreement> createRentalAgreement(@InputArgument RentalAgreementInput rentalAgreementInput) throws ValidationException {
        Tool tool = toolRepository.findById(rentalAgreementInput.toolId()).block();
        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalAgreementInput)
                .validate()
                .finalizeAgreement(defaultHolidays());

        return rentalAgreementRepository.save(rentalAgreement);
    }
}