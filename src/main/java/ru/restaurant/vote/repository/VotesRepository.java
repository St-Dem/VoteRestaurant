package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Votes;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.model.Votes.RESTAURANT;

@Transactional(readOnly = true)
public interface VotesRepository extends BaseRepository<Votes> {
    Votes getVotesById(Integer id);

    @EntityGraph(RESTAURANT)
    List<Votes> getAllByUserId(Integer userId);

    @EntityGraph(RESTAURANT)
    Votes getVotesByUserIdAndDate(Integer userId, LocalDate localDate);

    @EntityGraph(RESTAURANT)
    Votes getVotesByUserIdAndId(Integer userId, Integer id);

    @EntityGraph(RESTAURANT)
    List<Votes> getAllByDate(LocalDate localDate);
}
