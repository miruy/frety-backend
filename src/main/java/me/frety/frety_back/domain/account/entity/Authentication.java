package me.frety.frety_back.domain.account.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.common.entity.BaseEntity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Authentication extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(STRING)
    @Column(name = "type")
    private AuthenticationType type;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;
}
