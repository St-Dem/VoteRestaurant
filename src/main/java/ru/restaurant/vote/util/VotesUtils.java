package ru.restaurant.vote.util;

import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.to.VotesDto;
import ru.restaurant.vote.to.VotesTo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.restaurant.vote.util.UserUtil.createDtoFromUser;
import static ru.restaurant.vote.util.UserUtil.createNewFromUser;

public class VotesUtils {

    public static VotesTo voteAsTo(Votes votes) {
        return new VotesTo(votes.getId(), votes.getDate(), votes.getUser(), votes.getRestaurant());
    }

    public static Votes voteToAsVote(VotesTo votesTo) {
        return new Votes(votesTo.getId(), votesTo.getDate(), votesTo.getUser(), votesTo.getRestaurant());
    }

    public static List<VotesTo> asTo(List<Votes> votes) {
        return votes.stream()
                .map(VotesUtils::voteAsTo)
                .collect(Collectors.toList());
    }

    public static List<Votes> toAsVotes(List<VotesTo> votesTo){
        return votesTo.stream()
                .map((VotesUtils::voteToAsVote))
                .collect(Collectors.toList());
    }

    public static VotesDto voteAsDto(Votes votes){
        return new VotesDto(votes.getId(), votes.getDate(), createDtoFromUser(votes.getUser()), votes.getRestaurant());
    }

    public static List<VotesDto> asDto(List<Votes> votes){
     return votes.stream()
                .map(VotesUtils::voteAsDto)
                .collect(Collectors.toList());
    }
}
