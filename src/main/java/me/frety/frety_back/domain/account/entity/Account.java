package me.frety.frety_back.domain.account.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.auth.entity.Authentication;
import me.frety.frety_back.domain.common.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Account extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Privacy privacy;

    @Builder.Default
    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private Set<Authentication> authentications = new HashSet<>();

    public void addAuthentication(Authentication authentication) {
        authentication.connectAccount(this);
        authentications.add(authentication);
    }

    public void connectPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public boolean isUser() {
        return this.role == Role.USER;
    }
}
