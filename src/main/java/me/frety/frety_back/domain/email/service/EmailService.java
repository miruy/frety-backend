package me.frety.frety_back.domain.email.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.email.usecase.EmailUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailService implements EmailUseCase {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Transactional
    public void sendEmail(String to, String subject, String body) {

        /**
         * 이메일 전송
         */
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true로 설정하여 HTML로 인식됨
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
