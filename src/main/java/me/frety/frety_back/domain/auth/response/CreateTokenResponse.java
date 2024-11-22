package me.frety.frety_back.domain.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long authId; // 로그인한 유저아이디
}
