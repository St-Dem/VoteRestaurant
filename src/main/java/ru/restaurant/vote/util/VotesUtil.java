package ru.restaurant.vote.util;

import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.to.UserTo;
import ru.restaurant.vote.to.VotesDto;
import ru.restaurant.vote.to.VotesTo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.restaurant.vote.util.UserUtil.createNewFromUser;

public class VotesUtil {
    public static VotesTo voteAsTo(Votes votes) {
        return new VotesTo(votes.id(), votes.getDate(), votes.getUser(), votes.getRestaurant());
    }

    public static Votes voteToAsVote(VotesTo votesTo) {
        return new Votes(votesTo.id(), votesTo.getDate(), votesTo.getUser(), votesTo.getRestaurant());
    }

    public static List<VotesTo> asTo(List<Votes> votes) {
        return votes.stream()
                .map(VotesUtil::voteAsTo)
                .collect(Collectors.toList());
    }

    public static List<Votes> toAsVotes(List<VotesTo> votesTo){
        return votesTo.stream()
                .map((VotesUtil::voteToAsVote))
                .collect(Collectors.toList());
    }

    public static VotesDto voteAsDto(Votes votes){
        return new VotesDto(votes.id(), votes.getDate(), createNewFromUser(votes.getUser()), votes.getRestaurant());
    }

    public static List<VotesDto> asDto(List<Votes> votes){
     return votes.stream()
                .map(VotesUtil::voteAsDto)
                .collect(Collectors.toList());
    }
}
