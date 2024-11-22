package me.frety.frety_back.domain.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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

    @Builder.Default
    @OneToMany(mappedBy = "authentication", cascade = CascadeType.PERSIST)
    private List<Token> tokens = new ArrayList<>();

    public void connectAccount(Account account) {
        if (this.account != null) {
            throw new RuntimeException("이미 계정이 연결되어있습니다.");
        }

        this.account = account;
    }

    public void addToken(Token token) {
        token.connectAuthentication(this);
        tokens.add(token);
    }
}
