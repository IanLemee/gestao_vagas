package tech.ian.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class AuthCompanyDto {
    private String username;
    private String password;
}
