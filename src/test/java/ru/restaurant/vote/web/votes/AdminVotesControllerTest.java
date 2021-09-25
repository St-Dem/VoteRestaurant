package ru.restaurant.vote.web.votes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant.vote.repository.VotesRepository;
import ru.restaurant.vote.web.AbstractControllerTest;
import ru.restaurant.vote.web.menu.RestMenuController;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant.vote.web.user.UserTestData.ADMIN_MAIL;
import static ru.restaurant.vote.web.user.UserTestData.USER_MAIL;
import static ru.restaurant.vote.web.votes.AdminVotesController.REST_URL;
import static ru.restaurant.vote.web.votes.VotesTestData.*;

public class AdminVotesControllerTest extends AbstractControllerTest {

    @Autowired
    VotesRepository votesRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(listVotes));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAllUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getById() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/2"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(votes2));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getVotesBetweenDate() throws Exception {
        perform(MockMvcRequestBuilders.get(RestMenuController.REST_URL + "/dateBetween")
                .param("startDate", "2021-09-01")
                .param("endDate", String.valueOf(LocalDate.now().minusDays(1))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(listBetween));
    }
}
