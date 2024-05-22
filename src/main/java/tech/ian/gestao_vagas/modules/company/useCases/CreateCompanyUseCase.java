package tech.ian.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.exceptions.UserFoundException;
import tech.ian.gestao_vagas.modules.company.entities.CompanyEntity;
import tech.ian.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository repository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.repository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.repository.save(companyEntity);
    }
}
