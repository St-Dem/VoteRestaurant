package ru.restaurant.vote.web.votes;

import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.to.VotesTo;
import ru.restaurant.vote.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.web.menu.MenuTestData.CREATED;
import static ru.restaurant.vote.web.restaurant.RestaurantTestData.*;
import static ru.restaurant.vote.web.user.UserTestData.admin;
import static ru.restaurant.vote.web.user.UserTestData.user;

public class VotesTestData {
    public static final MatcherFactory.Matcher<Votes> VOTES_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Votes.class, "user", "restaurant");
public static final MatcherFactory.Matcher<VotesTo> VOTES_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VotesTo.class, "user", "restaurant");

    public static final int FIRST_VOTES_ID = 1;
    public static final int FIVE_VOTES_ID = 1 + 4;

    public static final Votes votes1 = new Votes(FIRST_VOTES_ID, CREATED, user, first_restaurant);
    public static final Votes votes2 = new Votes(FIRST_VOTES_ID + 1, CREATED, admin, first_restaurant);
    public static final Votes votes3 = new Votes(FIRST_VOTES_ID + 2, LocalDate.of(2021, 9, 2), admin, third_restaurant);
    public static final Votes votes4 = new Votes(FIRST_VOTES_ID + 3, LocalDate.of(2021, 9, 3), user, second_restaurant);
    public static final Votes votes5 = new Votes(FIVE_VOTES_ID, LocalDate.now(), admin, second_restaurant);

    public static final List<Votes> listVotes = List.of(votes1, votes2, votes3, votes4, votes5);
    public static final List<Votes> listBetween = List.of(votes1, votes2, votes3, votes4);
    public static final List<Votes> listUserBetween = List.of(votes1, votes4);


    public static Votes getNew() {
        return new Votes(null, LocalDate.now(), user, first_restaurant);
    }

    public static Votes getUpdated() {
        return votes5;
    }
}
