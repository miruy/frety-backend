package me.frety.frety_back.domain.account.repository;

import me.frety.frety_back.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
