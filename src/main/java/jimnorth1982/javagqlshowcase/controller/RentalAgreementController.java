package jimnorth1982.javagqlshowcase.controller;

import jimnorth1982.javagqlshowcase.model.RentalAgreement;
import jimnorth1982.javagqlshowcase.repo.RentalAgreementRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class RentalAgreementController {
    private final RentalAgreementRepository rentalAgreementRepository;

    @QueryMapping
    public RentalAgreement findRentalAgreementById(@Argument Long id) {
        return rentalAgreementRepository.findById(id);
    }

    @QueryMapping
    public List<RentalAgreement> findAllRentalAgreements() {
        return rentalAgreementRepository.findAll();
    }

}
