package me.frety.frety_back.domain.account.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.frety.frety_back.domain.account.entity.Role;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccount {
    @NotNull(message = "role is required")
    private Role role;

    @NotNull(message = "name is required")
    private String name;
}
