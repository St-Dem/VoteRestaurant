package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Votes;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.restaurant.vote.model.Votes.RESTAURANT;

@Transactional(readOnly = true)
public interface VotesRepository extends BaseRepository<Votes> {
    @EntityGraph(RESTAURANT)
    List<Votes> getAllByUserId(int userId);

    @EntityGraph(RESTAURANT)
    Optional<Votes> getVotesByUserIdAndDate(int userId, LocalDate localDate);

    @EntityGraph(RESTAURANT)
    List<Votes> getVotesByUserIdAndDateBetweenOrderByDate(int userId, LocalDate startDate, LocalDate endDate);

    @EntityGraph(RESTAURANT)
    Votes getVotesByUserIdAndId(int userId, Integer id);

    @EntityGraph(attributePaths = {"restaurant", "user"},  type = EntityGraph.EntityGraphType.LOAD)
    List<Votes> getAllByDate(LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant", "user"},  type = EntityGraph.EntityGraphType.LOAD)
    List<Votes> getAllByDateBetweenOrderByDate(LocalDate startDate, LocalDate endDate);
}
