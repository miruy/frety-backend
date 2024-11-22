package me.frety.frety_back.domain.vote.repository;

import me.frety.frety_back.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
