package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.AuthSpec;
import me.frety.frety_back.domain.auth.request.CreateTokenRequest;
import me.frety.frety_back.domain.auth.response.CreateTokenResponse;
import me.frety.frety_back.domain.auth.usecase.AuthUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi implements AuthSpec {
    private final AuthUseCase authUseCase;

    @PostMapping("/token")
    public ResponseEntity<CreateTokenResponse> createToken(@RequestBody CreateTokenRequest request) {
        CreateTokenResponse body = authUseCase.createToken(request);

        return ResponseEntity.ok(body);
    }
}
