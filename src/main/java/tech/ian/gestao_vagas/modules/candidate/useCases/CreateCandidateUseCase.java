package tech.ian.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.exceptions.UserFoundException;
import tech.ian.gestao_vagas.modules.candidate.entity.CandidateEntity;
import tech.ian.gestao_vagas.modules.candidate.repositiry.CandidateRepository;
@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository repository;
    public CandidateEntity excute(CandidateEntity candidateEntity) {
        repository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        return repository.save(candidateEntity);
    }
}
