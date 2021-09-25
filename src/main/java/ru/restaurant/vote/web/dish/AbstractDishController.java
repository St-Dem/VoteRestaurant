package ru.restaurant.vote.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import ru.restaurant.vote.model.Dish;
import ru.restaurant.vote.repository.DishRepository;
import ru.restaurant.vote.repository.MenuRepository;
import ru.restaurant.vote.to.DishTo;


public abstract class AbstractDishController {
    @Autowired
    protected DishRepository dishRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Dish fromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice(), menuRepository.getById(dishTo.getMenuId()));
    }
}
