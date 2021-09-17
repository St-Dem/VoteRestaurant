package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.model.Menu.RESTAURANT_AND_DISH;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @EntityGraph(RESTAURANT_AND_DISH)
    Menu getMenuById(Integer id);

    @EntityGraph(RESTAURANT_AND_DISH)
    List<Menu> getAllByRestaurantIdAndDate(int restaurantId, LocalDate localDate);

    @EntityGraph(RESTAURANT_AND_DISH)
    List<Menu> getAllByRestaurantId(int id);

    @EntityGraph(RESTAURANT_AND_DISH)
    List<Menu> getAllByDateBetweenOrderByDate(LocalDate begin, LocalDate end);

    @EntityGraph(RESTAURANT_AND_DISH)
    List<Menu> getAllByDate(LocalDate localDate);

    @EntityGraph(RESTAURANT_AND_DISH)
    List<Menu> getAllByRestaurantIdAndDateBetweenOrderByDateDesc(int id, LocalDate start, LocalDate end);
}
