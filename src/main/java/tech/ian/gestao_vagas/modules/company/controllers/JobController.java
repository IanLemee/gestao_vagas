package tech.ian.gestao_vagas.modules.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
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
