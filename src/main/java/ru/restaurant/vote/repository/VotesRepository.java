package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Votes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VotesRepository extends BaseRepository<Votes> {
    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Votes> getAllByUserId(int userId);

    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Votes> getVotesByUserIdAndDate(int userId, LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Votes> getVotesByUserIdAndDateBetweenOrderByDate(int userId, LocalDate startDate, LocalDate endDate);

    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    Votes getVotesByUserIdAndId(int userId, Integer id);

    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Votes> getAllByDate(LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Votes> getAllByDateBetweenOrderByDate(LocalDate startDate, LocalDate endDate);
}
