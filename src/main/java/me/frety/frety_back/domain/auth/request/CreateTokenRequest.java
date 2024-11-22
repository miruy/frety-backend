package me.frety.frety_back.domain.auth.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTokenRequest {
    @NotNull(message = "type is required")
    private GrantType type;

    @NotNull(message = "loginId is required")
    private String loginId;

    @NotNull(message = "password is required")
    private String password;
}
