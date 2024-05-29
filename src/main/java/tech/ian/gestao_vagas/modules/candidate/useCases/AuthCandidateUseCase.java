package tech.ian.gestao_vagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ian.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDto;
import tech.ian.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDto;
import tech.ian.gestao_vagas.modules.candidate.repository.CandidateRepository;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDto execute(AuthCandidateRequestDto authCandidateRequestDto) throws AuthenticationException {
        var candidate = this.repository.findByUsername(authCandidateRequestDto.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/Password incorrect");
                });

        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDto.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(15));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var AuthCandidateResponse = AuthCandidateResponseDto.builder()
                .acess_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return AuthCandidateResponse;
    }
}
