package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.auth.request.CreateTokenRequest;
import me.frety.frety_back.domain.auth.response.CreateTokenResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth", description = "보안")
public interface AuthSpec {
    @Operation(summary = "토큰 생성")
    ResponseEntity<CreateTokenResponse> createToken(CreateTokenRequest request);
}
