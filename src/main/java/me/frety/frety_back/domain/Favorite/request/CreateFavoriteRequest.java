package me.frety.frety_back.domain.Favorite.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.frety.frety_back.domain.Favorite.entity.FavoriteType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteRequest {
    @NotNull(message = "targetId is required")
    private Long targetId; // tabId

    @NotNull(message = "type is required")
    private FavoriteType type;

    @NotNull(message = "favoriterId is required")
    private Long favoriterId;
}
