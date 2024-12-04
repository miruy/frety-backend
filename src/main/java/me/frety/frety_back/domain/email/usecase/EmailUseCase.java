package me.frety.frety_back.domain.email.usecase;

import org.springframework.validation.annotation.Validated;

@Validated
public interface EmailUseCase {
    void sendEmail(String to, String subject, String body);
}
