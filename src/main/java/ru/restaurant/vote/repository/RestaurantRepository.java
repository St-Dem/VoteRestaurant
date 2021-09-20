package ru.restaurant.vote.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Restaurant;

@Transactional
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    Restaurant getById(int id);
}
