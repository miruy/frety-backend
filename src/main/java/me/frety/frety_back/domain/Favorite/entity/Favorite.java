package me.frety.frety_back.domain.Favorite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.common.entity.BaseEntity;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.vote.entity.VoteType;

import java.util.Optional;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Favorite extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    @JsonIgnore
    private Tab tab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favoriter_id")
    private Account favoriter;

    @Enumerated(STRING)
    @Column(name = "type")
    private FavoriteType type;

    public void softDelete() {
        super.softDelete();
    }

    public void connectTab(Tab tab) {
        this.type = FavoriteType.TAB;
        this.tab = tab;
    }

    // 삭제 상태를 복구
    public void recover() {
        if (this.isDeleted()) {
            this.setDeletedAt(null);
        }
    }
}
