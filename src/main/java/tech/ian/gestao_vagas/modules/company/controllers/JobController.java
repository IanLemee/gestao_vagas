package tech.ian.gestao_vagas.modules.company.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ian.gestao_vagas.modules.company.dto.CreateJobDto;
import tech.ian.gestao_vagas.modules.company.entities.JobEntity;
import tech.ian.gestao_vagas.modules.company.repositories.JobRepository;
import tech.ian.gestao_vagas.modules.company.useCases.CreateJobUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informacoes das vagas")
    @Operation(summary = "Cadastro de vaga",
            description = "Essa funcao e responsavel por cadastrar as vagas dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobEntity create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        //jobEntity.setCompanyId(UUID.fromString(companyId.toString()));

        var jobEntity = JobEntity.builder()
                .benefits(createJobDto.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDto.getDescription())
                .level(createJobDto.getLevel())
                .build();
        return this.createJobUseCase.execute(jobEntity);
    }
}
