package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Menu;

import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    Menu getById(int id);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByRestaurantIdAndDate(int restaurantId, LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByRestaurantId(int id);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByDateBetweenOrderByDate(LocalDate begin, LocalDate end);
}
