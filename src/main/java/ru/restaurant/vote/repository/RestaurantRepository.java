package ru.restaurant.vote.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.vote.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

}
