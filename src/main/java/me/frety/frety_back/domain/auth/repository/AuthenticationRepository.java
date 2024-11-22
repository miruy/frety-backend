package me.frety.frety_back.domain.auth.repository;

import me.frety.frety_back.domain.auth.entity.Authentication;
import me.frety.frety_back.domain.auth.entity.AuthenticationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
    boolean existsByLoginId(String loginId);

    Optional<Authentication> findByTypeAndLoginId(AuthenticationType authenticationType, String loginId);
}
