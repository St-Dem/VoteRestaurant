package ru.restaurant.vote.web.dish;

import ru.restaurant.vote.model.Dish;
import ru.restaurant.vote.web.MatcherFactory;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int DISH_ID = 1;
    public static final String FAT_FOOD = "fat food";
    public static final String ENGINEER_FOOD = "engineer food";

    public static final Dish dish1 = new Dish(DISH_ID, FAT_FOOD, 100);
    public static final Dish dish2 = new Dish(DISH_ID + 1, "great food", 200);
    public static final Dish dish3 = new Dish(DISH_ID + 2, ENGINEER_FOOD, 250);
    public static final Dish dish4 = new Dish(DISH_ID + 3, "food", 70);
    public static final Dish dish5 = new Dish(DISH_ID + 4, "bad food", 50);
    public static final Dish dish6 = new Dish(DISH_ID + 5, "meat", 300);
    public static final Dish dish7 = new Dish(DISH_ID + 6, FAT_FOOD, 100);
    public static final Dish dish8 = new Dish(DISH_ID + 7, FAT_FOOD, 90);
    public static final Dish dish9 = new Dish(DISH_ID + 8, ENGINEER_FOOD, 250);

    public static Dish getNew() {
        return new Dish(null, "new food", 999);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID + 9, "UpdatedName", 888);
    }
}
