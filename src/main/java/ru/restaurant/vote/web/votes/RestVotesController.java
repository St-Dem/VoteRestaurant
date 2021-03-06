package ru.restaurant.vote.web.votes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.vote.error.IllegalRequestDataException;
import ru.restaurant.vote.model.Restaurant;
import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.repository.RestaurantRepository;
import ru.restaurant.vote.repository.VotesRepository;
import ru.restaurant.vote.to.VotesTo;
import ru.restaurant.vote.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.util.InvalidTimeUtil.beforeInvalidTime;
import static ru.restaurant.vote.util.VotesUtils.asTo;
import static ru.restaurant.vote.util.VotesUtils.voteAsTo;
import static ru.restaurant.vote.web.SecurityUtil.authId;
import static ru.restaurant.vote.web.URLPattern.URL;

@RestController
@RequestMapping(value = RestVotesController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestVotesController {
    public static final String REST_URL = URL + "/votes";

    private final VotesRepository votesRepository;
    private final RestaurantRepository restaurantRepository;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @RequestParam int id) {
        int userId = authUser.id();
        log.info("delete votes {} for user {} ", id, userId);

        if (beforeInvalidTime()) {
            votesRepository.getVotesByUserIdAndId(userId, id)
                    .orElseThrow(() -> new IllegalRequestDataException("Vote not found"));
            votesRepository.delete(id);
        } else {
            throw new IllegalRequestDataException("You late");
        }
    }

    @GetMapping("/user")
    public List<VotesTo> getWithUser() {
        int authId = authId();
        log.info("get all votes for user {}", authId);
        return asTo(votesRepository.getAllByUserId(authId));
    }

    @GetMapping("/dateBetween")
    public List<VotesTo> getVotesBetweenDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("get votes between {} and {}", startDate, endDate);
        return asTo(votesRepository.getVotesByUserIdAndDateBetweenOrderByDate(authId(), startDate, endDate));
    }

    @GetMapping("/{id}")
    public VotesTo get(@PathVariable int id) {
        log.info("get votesById {} ", id);
        return voteAsTo(votesRepository.getVotesByUserIdAndId(authId(), id)
                .orElseThrow(() -> new IllegalRequestDataException("Votes not found")));
    }


    @PostMapping
    public ResponseEntity<VotesTo> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int id = authUser.id();
        log.info("create votes for user {}", id);

        Votes newVotes = votesRepository.getVotesByUserIdAndDate(id, LocalDate.now()).orElse(null);

        if (newVotes != null) {
            throw new IllegalRequestDataException("You voted today");
        }

        Votes created = votesRepository.save(new Votes(null, LocalDate.now(), authUser.getUser(), restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalRequestDataException("Restaurant not found"))));
        VotesTo createdTo = voteAsTo(created);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdTo.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(createdTo);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int userId = authUser.id();
        log.info("update vote for user {}, restaurant {}", userId, restaurantId);

        if (beforeInvalidTime()) {
            Votes newVotes = votesRepository.getVotesByUserIdAndDate(userId, LocalDate.now())
                    .orElseThrow(() -> new IllegalRequestDataException("Vote not found"));
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new IllegalRequestDataException("Restaurant not found"));
            newVotes.setRestaurant(restaurant);
            votesRepository.save(newVotes);
        } else {
            throw new IllegalRequestDataException("You late");
        }
    }
}
