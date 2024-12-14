package me.frety.frety_back.domain.tab.repository;

import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TabQueryDSLRepository {
    Page<Tab> search(Pageable pageable, SearchTabsCondition condition);

    Page<Tab> searchByAuthor(Pageable pageable, String authorName);
}
