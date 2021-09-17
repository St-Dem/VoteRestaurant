package ru.restaurant.vote.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    Dish getDishById(Integer id);
}
