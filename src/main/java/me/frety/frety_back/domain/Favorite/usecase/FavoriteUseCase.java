package me.frety.frety_back.domain.Favorite.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.Favorite.request.CreateFavoriteRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface FavoriteUseCase {
    Long createFavorite(@Valid CreateFavoriteRequest request, Long authenticatedAccountId);

    void deleteFavorite(Long accountId, Long tabId);

    Boolean getFavorite(Long favoriterId, Long tabId);
}
