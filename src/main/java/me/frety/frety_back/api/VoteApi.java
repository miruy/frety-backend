package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.VoteSpec;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import me.frety.frety_back.domain.vote.usecase.VoteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteApi implements VoteSpec {
    private final VoteUseCase voteUseCase;

    @PostMapping
    public ResponseEntity<Long> createVote(@RequestBody CreateVoteRequest request, @AuthenticationPrincipal Jwt jwt) {
        Long voterId = voteUseCase.createVote(request, Long.valueOf(jwt.getSubject()));

        return ResponseEntity.created(URI.create(voterId.toString())).body(voterId);
    }
}
