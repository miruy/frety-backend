package me.frety.frety_back.domain.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull(message = "name is required")
    private String name;
}
