package ru.restaurant.vote.web.restaurant;

import ru.restaurant.vote.model.Restaurant;
import ru.restaurant.vote.model.Role;
import ru.restaurant.vote.model.User;
import ru.restaurant.vote.util.JsonUtil;
import ru.restaurant.vote.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class,  "menu", "votes");

    public static final int FIRST_RESTAURANT_ID = 1;
    public static final int SECOND_RESTAURANT_ID = 2;
    public static final int THIRD_RESTAURANT_ID = 3;

    public static final String FIRST_RESTAURANT_NAME = "grandRestaurant";
    public static final String SECOND_RESTAURANT_NAME = "restaurant";
    public static final String THIRD_RESTAURANT_NAME = "likeCafe";

    public static final Restaurant first_restaurant = new Restaurant(FIRST_RESTAURANT_ID, FIRST_RESTAURANT_NAME,"Рублевка" );
    public static final Restaurant second_restaurant = new Restaurant(SECOND_RESTAURANT_ID, SECOND_RESTAURANT_NAME, "внутри 3 кольца");
    public static final Restaurant third_restaurant = new Restaurant(THIRD_RESTAURANT_ID, THIRD_RESTAURANT_NAME, "спальный район");

    public static final  List<Restaurant> restaurants = List.of(first_restaurant, second_restaurant, third_restaurant);

    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant", "Красная площадь");
    }

    public static Restaurant getUpdated() {
        return third_restaurant;
    }
}
