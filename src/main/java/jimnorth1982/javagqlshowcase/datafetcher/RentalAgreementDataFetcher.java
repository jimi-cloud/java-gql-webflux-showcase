package jimnorth1982.javagqlshowcase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import jimnorth1982.javagqlshowcase.model.RentalAgreement;
import jimnorth1982.javagqlshowcase.model.RentalAgreementInput;
import jimnorth1982.javagqlshowcase.model.Tool;
import jimnorth1982.javagqlshowcase.repo.RentalAgreementRepository;
import jimnorth1982.javagqlshowcase.repo.ToolRepository;
import jimnorth1982.javagqlshowcase.validation.ValidationException;
import lombok.AllArgsConstructor;

import java.util.List;

import static jimnorth1982.javagqlshowcase.calendar.Holidays.defaultHolidays;

@AllArgsConstructor
@DgsComponent
public class RentalAgreementDataFetcher {
    private final RentalAgreementRepository rentalAgreementRepository;
    private final ToolRepository toolRepository;

    @DgsQuery
    public RentalAgreement findRentalAgreementById(DataFetchingEnvironment dfe, @InputArgument Integer id) {
        return rentalAgreementRepository.findById(id);
    }

    @DgsQuery
    public List<RentalAgreement> findAllRentalAgreements(DataFetchingEnvironment dfe) {
        return rentalAgreementRepository.findAll();
    }

    @DgsMutation
    public RentalAgreement createRentalAgreement(DataFetchingEnvironment dfe, @InputArgument RentalAgreementInput rentalAgreementInput) throws ValidationException {
        Tool tool = toolRepository.findById(rentalAgreementInput.toolId());
        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalAgreementInput)
                .validate()
                .finalizeAgreement(defaultHolidays());

        return rentalAgreementRepository.save(rentalAgreement);
    }
}