package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.FavoriteSpec;
import me.frety.frety_back.api.spec.VoteSpec;
import me.frety.frety_back.domain.Favorite.entity.Favorite;
import me.frety.frety_back.domain.Favorite.request.CreateFavoriteRequest;
import me.frety.frety_back.domain.Favorite.usecase.FavoriteUseCase;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import me.frety.frety_back.domain.vote.usecase.VoteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteApi implements FavoriteSpec {
    private final FavoriteUseCase favoriteUseCase;

    @PostMapping
    public ResponseEntity<Long> createFavorite(@RequestBody CreateFavoriteRequest request, @AuthenticationPrincipal Jwt jwt) {
        Long userId = favoriteUseCase.createFavorite(request, Long.valueOf(jwt.getSubject()));

        return ResponseEntity.created(URI.create(userId.toString())).body(userId);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFavorite(@PathVariable("favoriterId") Long favoriterId, @PathVariable("tabId") Long tabId) {
        favoriteUseCase.deleteFavorite(favoriterId, tabId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Boolean> getFavorite(@PathVariable("favoriterId") Long favoriterId,
                                               @PathVariable("tabId") Long tabId) {
        Boolean body = favoriteUseCase.getFavorite(favoriterId, tabId);

        return ResponseEntity.ok(body);
    }
}
