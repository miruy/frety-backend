package me.frety.frety_back.domain.auth.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.frety.frety_back.domain.auth.entity.AuthenticationType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthenticationRequest {
    @NotNull(message = "accountId is required")
    private Long accountId;

    @NotNull(message = "type is required")
    private AuthenticationType type;

    private String loginId;
    private String password;
}
