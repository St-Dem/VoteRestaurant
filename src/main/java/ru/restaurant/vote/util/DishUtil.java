package ru.restaurant.vote.util;

import ru.restaurant.vote.model.Dish;
import ru.restaurant.vote.to.DishTo;

import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {
    public static DishTo dishAsTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice(), dish.getMenu().getId());
    }

    public static List<DishTo> asTo(List<Dish> dishes) {
        return dishes.stream()
                .map(DishUtil::dishAsTo)
                .collect(Collectors.toList());
    }
}
