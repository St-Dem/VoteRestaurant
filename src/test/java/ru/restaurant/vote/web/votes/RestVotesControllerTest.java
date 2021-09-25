package ru.restaurant.vote.web.votes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.repository.VotesRepository;
import ru.restaurant.vote.web.AbstractControllerTest;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant.vote.util.InvalidTimeUtil.alwaysValid;
import static ru.restaurant.vote.util.InvalidTimeUtil.beforeInvalidTime;
import static ru.restaurant.vote.web.restaurant.RestaurantTestData.FIRST_RESTAURANT_ID;
import static ru.restaurant.vote.web.restaurant.RestaurantTestData.first_restaurant;
import static ru.restaurant.vote.web.user.UserTestData.ADMIN_MAIL;
import static ru.restaurant.vote.web.user.UserTestData.USER_MAIL;
import static ru.restaurant.vote.web.votes.RestVotesController.REST_URL;
import static ru.restaurant.vote.web.votes.VotesTestData.*;

public class RestVotesControllerTest extends AbstractControllerTest {
    @Autowired
    VotesRepository votesRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getWithUser() throws Exception {
        List<Votes> votesUserList = List.of(votes1, votes4);
        perform(MockMvcRequestBuilders.get(REST_URL + "/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(votesUserList));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/4"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(votes4));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getVotesBetweenDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/dateBetween")
                .param("startDate", "2021-09-01")
                .param("endDate", String.valueOf(LocalDate.now().minusDays(1))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(listUserBetween));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        if (beforeInvalidTime()) {
            perform(MockMvcRequestBuilders.delete(REST_URL))
                    .andExpect(status().isNoContent());

            assertFalse(votesRepository.findById(FIVE_VOTES_ID).isPresent());
        } else {
            perform(MockMvcRequestBuilders.delete(REST_URL))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(content().string(containsString("You late")));
        }
    }


    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteNotFound() throws Exception {
        alwaysValid();

        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Vote not found")));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithLocation() throws Exception {
        Votes newVotes = getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "1"))
                .andExpect(status().isCreated());

        Votes created = VOTES_MATCHER.readFromJson(action);
        int newId = created.id();
        newVotes.setId(newId);

        VOTES_MATCHER.assertMatch(created, newVotes);
        VOTES_MATCHER.assertMatch(votesRepository.getById(newId), newVotes);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Votes updated = getUpdated();
        updated.setRestaurant(first_restaurant);

        if (beforeInvalidTime()) {
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", String.valueOf(FIRST_RESTAURANT_ID)))
                    .andDo(print())
                    .andExpect(status().isNoContent());
            VOTES_MATCHER.assertMatch(votesRepository.getById(FIVE_VOTES_ID), updated);
        } else {
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", String.valueOf(FIRST_RESTAURANT_ID)))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(content().string(containsString("You late")));
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateNotFound() throws Exception {
        alwaysValid();

        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("restaurantId", String.valueOf(FIRST_RESTAURANT_ID)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Vote not found")));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateRestaurantNotFound() throws Exception {
        alwaysValid();

        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("restaurantId", "4"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Restaurant not found")));
    }
}
