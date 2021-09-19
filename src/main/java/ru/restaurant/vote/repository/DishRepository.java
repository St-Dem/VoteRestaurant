package ru.restaurant.vote.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Dish;

import java.util.List;

import static ru.restaurant.vote.model.Dish.MENU_DISH;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    Dish getDishById(int id);

    List<Dish> getAllByMenuId(int id);
}
