package tech.ian.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.modules.company.entities.JobEntity;
import tech.ian.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository repository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.repository.save(jobEntity);
    }
}
