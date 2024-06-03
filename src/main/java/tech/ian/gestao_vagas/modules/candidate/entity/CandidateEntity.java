package tech.ian.gestao_vagas.modules.candidate.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
@Table(name = "TB_CANDIDATES")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Ian Francisco de Campos", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "IanFcampos", requiredMode = Schema.RequiredMode.REQUIRED, description = "username do candidato")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(example = "ian.fcampos@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(example = "admin1234", minLength = 10, maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    private String password;
    @Schema(example = "dev java senior")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}