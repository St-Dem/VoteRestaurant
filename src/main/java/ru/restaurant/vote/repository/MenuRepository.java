package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Menu;

import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    Menu getMenuById(Integer id);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByRestaurantIdAndDate(int restaurantId, LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByRestaurantId(int id);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByDateBetweenOrderByDate(LocalDate begin, LocalDate end);

    @EntityGraph(attributePaths = {"restaurant", "dish"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "SELECT m FROM Menu m  WHERE m.date=:date  ORDER BY m.restaurant.name DESC")
    List<Menu> getAllByDate(@Param("date")LocalDate localDate);
}
