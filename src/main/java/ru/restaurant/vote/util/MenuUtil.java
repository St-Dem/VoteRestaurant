package ru.restaurant.vote.util;

import ru.restaurant.vote.model.Menu;
import ru.restaurant.vote.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {
    public static MenuTo menuAsTo(Menu menu) {
        return new MenuTo(menu.id(), menu.getRestaurant(), menu.getDate(), menu.getDish());
    }

    public static Menu menuToAsMenu(MenuTo menuTo) {
        return new Menu(menuTo.id(), menuTo.getRestaurant(), menuTo.getDate(), menuTo.getDish());
    }

    public static List<MenuTo> asTo(List<Menu> menus) {
        return menus.stream()
                .map(MenuUtil::menuAsTo)
                .collect(Collectors.toList());
    }

    public static List<Menu> toAsMenu(List<MenuTo> menusTo) {
        return menusTo.stream()
                .map(MenuUtil::menuToAsMenu)
                .collect(Collectors.toList());
    }
}
