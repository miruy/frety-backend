package me.frety.frety_back.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.common.entity.BaseEntity;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Token extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TokenType type;

    @Column(name = "value")
    private String value;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    public void connectAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
