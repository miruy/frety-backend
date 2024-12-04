package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.email.request.SendEmailRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Email", description = "이메일")
public interface EmailSpec {
    @Operation(summary = "이메일 전송")
    ResponseEntity<Void> sendEmail(@Parameter(name = "chord") SendEmailRequest request);
}
