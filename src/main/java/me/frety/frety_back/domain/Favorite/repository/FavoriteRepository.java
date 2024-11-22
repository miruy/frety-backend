package me.frety.frety_back.domain.Favorite.repository;

import me.frety.frety_back.domain.Favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByFavoriterIdAndTabId(Long favoriterId, Long tabId);
}
