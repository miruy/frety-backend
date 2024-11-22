package me.frety.frety_back.util.jwt;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
public class JWTUtil {
    public SecretKey createSecretKey(String secretKey) {
        return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }
}
