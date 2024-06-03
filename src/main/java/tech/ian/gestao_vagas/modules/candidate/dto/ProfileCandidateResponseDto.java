package tech.ian.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDto {

    @Schema(example = "Desenvolvedor Java")
    private String description;
    @Schema(example = "Ian")
    private String username;
    @Schema(example = "ianleme@hotmail.com")
    private String email;
    private UUID id;
    @Schema(example = "Ian Francisco de Campos")
    private String name;
}
