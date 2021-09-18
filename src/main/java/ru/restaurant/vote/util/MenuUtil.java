package ru.restaurant.vote.util;

import ru.restaurant.vote.model.Menu;
import ru.restaurant.vote.to.MenuTo;

public class MenuUtil {
    public static MenuTo menuAsTo(Menu menu) {
        return new MenuTo(menu.id(), menu.getRestaurant(), menu.getDate(), menu.getDish(), menu.getPrice());
    }

    public static Menu menuToAsMenu(MenuTo menuTo) {
        return new Menu(menuTo.id(), menuTo.getRestaurant(), menuTo.getDate(), menuTo.getDish(), menuTo.getPrice());
    }
}
