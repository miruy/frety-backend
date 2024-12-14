package me.frety.frety_back.domain.tab.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.common.config.QueryDSLConfig;
import me.frety.frety_back.domain.tab.entity.QTab;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.request.SearchMyCreatedTabsCondition;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition__Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static me.frety.frety_back.domain.tab.entity.QTab.tab;
import static me.frety.frety_back.domain.tab.request.SearchTabsCondition__Sort.RECENT;

@Repository
@RequiredArgsConstructor
public class TabQueryDSLRepositoryImpl implements TabQueryDSLRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Tab> search(Pageable pageable, SearchTabsCondition condition) {

        Predicate[] where = {
                containsKeyword(condition.getKeyword()),
                eqVoterId(condition.getVoterId()),
                eqFavoriterId(condition.getFavoriterId()),
                tab.deletedAt.isNull()
        };

        JPAQuery<Long> countQuery = queryFactory
                .select(tab.count())
                .from(tab)
                .where(where);

        JPAQuery<Tab> contentQuery = queryFactory
                .selectFrom(tab)
                .where(where)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sortRecent(condition.getSort()));

        return new PageImpl<>(
                contentQuery.fetch(), pageable, countQuery.fetchOne());
    }

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : tab.song.contains(keyword)
                .or(tab.artist.contains(keyword));
    }

    public BooleanExpression eqVoterId(Long voterId) {
        return voterId == null ? null : tab.votes.any().voter.id.eq(voterId)
                .and(tab.votes.any().deletedAt.isNull());
    }

    public BooleanExpression eqFavoriterId(Long favoriterId) {
        if (favoriterId == null) {
            return null;
        }

        // favoriterId가 일치하는 즐겨찾기와, 삭제되지 않은 즐겨찾기만 포함하도록 조건 추가
        return tab.favorites.any()
                .favoriter.id.eq(favoriterId)  // favoriterId가 일치하는 즐겨찾기
                .and(tab.favorites.any().deletedAt.isNull());  // 즐겨찾기가 삭제되지 않은 경우
    }

    public OrderSpecifier<?> sortRecent(SearchTabsCondition__Sort sort) {
        if (sort == null) {
            return null;
        }

        return sort == RECENT ?
                new OrderSpecifier<>(
                        com.querydsl.core.types.Order.DESC,
                        tab.id
                )
                :
                new OrderSpecifier<>(
                        com.querydsl.core.types.Order.DESC,
                        tab.votes.size()
                );
    }


    @Override
    public Page<Tab> searchByAuthor(Pageable pageable, String authorName) {

        Predicate[] where = {
                eqAuthorName(authorName), // authorName 조건 추가
                tab.deletedAt.isNull()
        };

        JPAQuery<Long> countQuery = queryFactory
                .select(tab.count())
                .from(tab)
                .where(where);

        JPAQuery<Tab> contentQuery = queryFactory
                .selectFrom(tab)
                .where(where)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sortRecent(RECENT));

        return new PageImpl<>(
                contentQuery.fetch(), pageable, countQuery.fetchOne());
    }

    private BooleanExpression eqAuthorName(String authorName) {
        return authorName == null ? null : tab.authorName.eq(authorName);
    }
}
