package tech.ian.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDto {
    @Schema(example = "Vaga para pessoa desenvolvedora java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Gympass, Vr, Va e Vt, plano odontologico", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Jr", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
