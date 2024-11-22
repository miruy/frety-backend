package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.UserSpec;
import me.frety.frety_back.domain.user.request.CreateUserRequest;
import me.frety.frety_back.domain.user.usecase.UserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi implements UserSpec {
    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody CreateUserRequest request) {
        Long userId = userUseCase.createUser(request);

        return ResponseEntity.created(URI.create(userId.toString())).body(userId);
    }
}
