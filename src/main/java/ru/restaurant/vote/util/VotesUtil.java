package ru.restaurant.vote.util;

import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.to.VotesTo;

import java.util.List;
import java.util.stream.Collectors;

public class VotesUtil {
    public static VotesTo VoteAsTo(Votes votes) {
        return new VotesTo(votes.id(), votes.getDate(), votes.getUser(), votes.getRestaurant());
    }

    public static Votes VoteToAsVote(VotesTo votesTo) {
        return new Votes(votesTo.id(), votesTo.getDate(), votesTo.getUser(), votesTo.getRestaurant());
    }

    public static List<VotesTo> asTo(List<Votes> votes) {
        return votes.stream()
                .map(VotesUtil::VoteAsTo)
                .collect(Collectors.toList());
    }
}
