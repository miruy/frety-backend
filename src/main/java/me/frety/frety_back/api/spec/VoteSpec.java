package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

@Tag(name = "Vote", description = "투표")
public interface VoteSpec {
    @Operation(summary = "투표 생성")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Long> createVote(CreateVoteRequest request, Jwt jwt);
}
