package me.frety.frety_back.domain.vote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.common.entity.BaseEntity;
import me.frety.frety_back.domain.tab.entity.Tab;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Vote extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    private Tab tab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id")
    private Account voter;

    @Enumerated(STRING)
    @Column(name = "type")
    private VoteType type;

    public void softDelete() {
        super.softDelete();
    }

    public void connectTab(Tab tab) {
        this.type = VoteType.TAB;
        this.tab = tab;
    }

}
