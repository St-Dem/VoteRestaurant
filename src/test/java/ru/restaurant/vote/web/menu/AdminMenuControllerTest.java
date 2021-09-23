package ru.restaurant.vote.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant.vote.model.Menu;
import ru.restaurant.vote.repository.MenuRepository;
import ru.restaurant.vote.to.MenuTo;
import ru.restaurant.vote.util.JsonUtil;
import ru.restaurant.vote.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant.vote.util.MenuUtil.menuAsTo;
import static ru.restaurant.vote.web.menu.AdminMenuController.REST_URL;
import static ru.restaurant.vote.web.menu.MenuTestData.*;
import static ru.restaurant.vote.web.restaurant.RestaurantTestData.first_restaurant;
import static ru.restaurant.vote.web.user.UserTestData.ADMIN_MAIL;

public class AdminMenuControllerTest extends AbstractControllerTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + THIRD_MENU_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(menuRepository.findById(THIRD_MENU_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Menu newMenu = getNew();
        MenuTo newTo = menuAsTo(newMenu);

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andExpect(status().isCreated());

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuRepository.getById(newId), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Menu updated = getUpdated();
        updated.setRestaurant(first_restaurant);
        MenuTo updatedTo = menuAsTo(updated);

        perform(MockMvcRequestBuilders.put(REST_URL + "/" + updatedTo.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(menuRepository.getById(updated.id()), updated);
    }
}
