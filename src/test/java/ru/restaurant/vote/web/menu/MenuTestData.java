package ru.restaurant.vote.web.menu;

import ru.restaurant.vote.model.Menu;
import ru.restaurant.vote.to.MenuTo;
import ru.restaurant.vote.web.MatcherFactory;
import ru.restaurant.vote.web.dish.DishTestData;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.web.dish.DishTestData.*;
import static ru.restaurant.vote.web.restaurant.RestaurantTestData.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class,  "dish", "restaurant");

    public static final int FIRST_MENU_ID = 1;
    public static final int THIRD_MENU_ID = FIRST_MENU_ID + 2;
    public static final int SIX_MENU_ID = FIRST_MENU_ID + 5;

    public static final LocalDate CREATED = LocalDate.of(2021, 9, 1);

    public static final Menu menu1 = new Menu(FIRST_MENU_ID, first_restaurant, CREATED, dish1, dish2, dish5);
    public static final Menu menu2 = new Menu(FIRST_MENU_ID + 1, second_restaurant, CREATED, dish3);
    public static final Menu menu3 = new Menu(THIRD_MENU_ID, third_restaurant, LocalDate.of(2021, 9, 2), dish4);
    public static final Menu menu4 = new Menu(FIRST_MENU_ID +3, second_restaurant, LocalDate.of(2021, 9, 3), dish6);
    public static final Menu menu5 = new Menu(FIRST_MENU_ID + 4, first_restaurant, LocalDate.now(), dish7);
    public static final Menu menu6 = new Menu(SIX_MENU_ID, second_restaurant, LocalDate.now(), dish8, dish9);

    public static final List<Menu> menuList = List.of(menu1, menu2, menu3, menu4, menu5, menu6);
    public static final List<Menu> todayMenu = List.of(menu5, menu6);
    public static final List<Menu> menuBetween = List.of(menu1, menu2, menu3, menu4);

    public static Menu getNew() {
        return new Menu(null, third_restaurant, LocalDate.of(2021, 9, 10), DishTestData.getNew());
    }

    public static Menu getUpdated() {
        return menu4;
    }
}
