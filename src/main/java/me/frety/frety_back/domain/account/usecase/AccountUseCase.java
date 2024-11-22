package me.frety.frety_back.domain.account.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.account.request.CreateAccount;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AccountUseCase {
    Long createAccount(@Valid CreateAccount request);
}
