package me.frety.frety_back.domain.user.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.user.request.CreateUserRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserUseCase {
    Long createUser(@Valid CreateUserRequest request);
}
