package me.frety.frety_back.domain.auth.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.auth.request.CreateTokenRequest;
import me.frety.frety_back.domain.auth.request.AddAuthenticationRequest;
import me.frety.frety_back.domain.auth.response.CreateTokenResponse;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthUseCase {
    void addAuthentication(@Valid AddAuthenticationRequest request);
    CreateTokenResponse createToken(@Valid CreateTokenRequest request);
}
