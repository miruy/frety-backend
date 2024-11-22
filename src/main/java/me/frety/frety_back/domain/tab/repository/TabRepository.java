package me.frety.frety_back.domain.tab.repository;

import me.frety.frety_back.domain.tab.entity.Tab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabRepository extends JpaRepository<Tab, Long>, TabQueryDSLRepository {
}
