package ru.restaurant.vote.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant.vote.repository.MenuRepository;
import ru.restaurant.vote.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant.vote.web.menu.MenuTestData.*;
import static ru.restaurant.vote.web.menu.RestMenuController.REST_URL;
import static ru.restaurant.vote.web.user.UserTestData.ADMIN_MAIL;
import static ru.restaurant.vote.web.user.UserTestData.USER_MAIL;

public class RestMenuControllerTest extends AbstractControllerTest {
    @Autowired
    MenuRepository menuRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getTodayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/today"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(todayMenu));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getTodayMenuWithAdmin() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/today"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(todayMenu));
    }

    @Test
    void getTodayMenuUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/today"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menuList));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/5"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu5));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getMenuBetweenDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/dateBetween")
                .param("startDate", "2021-09-01")
                .param("endDate", String.valueOf(LocalDate.now().minusDays(1))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menuBetween));
    }
}
