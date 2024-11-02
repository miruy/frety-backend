package me.frety.frety_back.domain.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull(message = "method is required")
    private UserAuthenticationMethod method;

    @NotNull(message = "loginId is required")
    private String loginId;

    @NotNull(message = "password is required")
    private String password;
}
