package tech.ian.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.exceptions.UserFoundException;
import tech.ian.gestao_vagas.modules.company.entities.CompanyEntity;
import tech.ian.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.repository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

                var password = passwordEncoder.encode(companyEntity.getPassword());
                companyEntity.setPassword(password);

        return this.repository.save(companyEntity);
    }
}
