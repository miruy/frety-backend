package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.user.request.CreateUserRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "User", description = "유저")
public interface UserSpec {
    @Operation(summary = "유저 생성")
    ResponseEntity<Long> createUser(CreateUserRequest request);
}
