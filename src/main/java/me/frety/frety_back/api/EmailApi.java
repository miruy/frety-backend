package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.EmailSpec;
import me.frety.frety_back.domain.email.request.SendEmailRequest;
import me.frety.frety_back.domain.email.usecase.EmailUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailApi implements EmailSpec {
    private final EmailUseCase emailUseCase;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody SendEmailRequest request) {

        String to = "dbflarla496695@gmail.com"; // 수신자 이메일 주소
        String subject = "[Frety] 기타 코드 신청"; // 이메일 제목
        String body = "<h1>신청코드 : " + request.getChord() + "</h1>"; // 이메일 내용

        emailUseCase.sendEmail(to, subject, body);

        return ResponseEntity.noContent().build();
    }
}
