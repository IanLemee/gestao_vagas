package tech.ian.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.modules.company.dto.AuthCompanyDto;
import tech.ian.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDto authCompanyDto) throws AuthenticationException{
        var company = this.repository.findByUsername(authCompanyDto.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Company not found");
                });

        // Verificar se senhas sao iguais
        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        // Se não for igual -> Erro
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        // Se for igual -> Gerar o token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);


        return token;
    }
}
