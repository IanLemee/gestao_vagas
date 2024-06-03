package tech.ian.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.modules.company.entities.JobEntity;
import tech.ian.gestao_vagas.modules.company.repositories.JobRepository;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository repository;

    public List<JobEntity> execute(String filter) {
        return repository.findByDescriptionContainingIgnoreCase(filter);
    }
}
