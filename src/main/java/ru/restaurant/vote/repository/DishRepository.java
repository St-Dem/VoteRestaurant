package ru.restaurant.vote.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    Dish getDishById(int id);

    List<Dish> getAllByMenuId(int id);
}
