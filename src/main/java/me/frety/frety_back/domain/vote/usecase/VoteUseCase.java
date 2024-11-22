package me.frety.frety_back.domain.vote.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface VoteUseCase {
    Long createVote(@Valid CreateVoteRequest request, Long authenticatedAccountId);
}
