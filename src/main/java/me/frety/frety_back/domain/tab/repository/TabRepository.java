package me.frety.frety_back.domain.tab.repository;

import me.frety.frety_back.domain.tab.entity.Tab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TabRepository extends JpaRepository<Tab, Long> {
    List<Tab> findAllByDeletedAtIsNull();
}
