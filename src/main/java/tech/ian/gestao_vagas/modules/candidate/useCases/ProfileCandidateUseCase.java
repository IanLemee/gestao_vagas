package tech.ian.gestao_vagas.modules.candidate.useCases;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDto;
import tech.ian.gestao_vagas.modules.candidate.repositiry.CandidateRepository;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    private CandidateRepository repository;

    public ProfileCandidateResponseDto execute(UUID idCandidate) {
        var candidate = this.repository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        var candidateDTO = ProfileCandidateResponseDto.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();

        return candidateDTO;
    }
}
