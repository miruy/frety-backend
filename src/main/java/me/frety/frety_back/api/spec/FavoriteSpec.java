package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.Favorite.entity.Favorite;
import me.frety.frety_back.domain.Favorite.request.CreateFavoriteRequest;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Favorite", description = "즐겨찾기")
public interface FavoriteSpec {
    @Operation(summary = "즐겨찾기 등록")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Long> createFavorite(CreateFavoriteRequest request, Jwt jwt);

    @Operation(summary = "즐겨찾기 해제")
    ResponseEntity<Void> deleteFavorite(
            @Parameter(name = "favoriterId") @RequestParam("favoriterId") Long favoriterId,
            @Parameter(name = "tabId") @RequestParam("tabId") Long tabId
    );

    @Operation(summary = "즐겨찾기 단건 조회")
    ResponseEntity<Boolean> getFavorite(
            @Parameter(name = "favoriterId") @RequestParam("favoriterId") Long favoriterId,
            @Parameter(name = "tabId") @RequestParam("tabId") Long tabId
    );
}
