package jimnorth1982.javagqlshowcase.repo;

import jimnorth1982.javagqlshowcase.model.RentalAgreement;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RentalAgreementRepository extends Repository<RentalAgreement, Long> {
    public RentalAgreement findById(Long id);

    public List<RentalAgreement> findAll();

    public RentalAgreement save(RentalAgreement rentalAgreementInput);
}
