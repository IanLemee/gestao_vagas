package tech.ian.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ian.gestao_vagas.modules.company.dto.AuthCompanyDto;
import tech.ian.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public String create(@RequestBody AuthCompanyDto authCompanyDto) throws AuthenticationException {
        return this.authCompanyUseCase.execute(authCompanyDto);
    }
}
