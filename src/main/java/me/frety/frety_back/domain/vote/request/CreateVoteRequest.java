package me.frety.frety_back.domain.vote.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.frety.frety_back.domain.vote.entity.VoteType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoteRequest {
    @NotNull(message = "targetId is required")
    private Long targetId; // tabId

    @NotNull(message = "type is required")
    private VoteType type;

    @NotNull(message = "voterId is required")
    private Long voterId;
}
