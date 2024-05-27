package tech.ian.gestao_vagas.modules.candidate.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ian.gestao_vagas.modules.candidate.entity.CandidateEntity;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);
}
