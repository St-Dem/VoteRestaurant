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
import org.webjars.NotFoundException;
import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.repository.RestaurantRepository;
import ru.restaurant.vote.repository.VotesRepository;
import ru.restaurant.vote.to.VotesDto;
import ru.restaurant.vote.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.util.VotesUtils.asDto;
import static ru.restaurant.vote.web.SecurityUtil.authId;
import static ru.restaurant.vote.web.URLPattern.ADMIN_URL;

@RestController
@RequestMapping(value = AdminVotesController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVotesController {
    public static final String REST_URL = ADMIN_URL + "/votes";

    private final VotesRepository votesRepository;
    private final RestaurantRepository restaurantRepository;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete votes {} ", id);
        votesRepository.delete(id);
    }

    @GetMapping("/{id}")
    public Votes get(@PathVariable int id) {
        log.info("get votesById {} ", id);
        return votesRepository.getById(id);
    }

    @GetMapping
    public List<VotesDto> getAll() {
        log.info("getAllVotes");
        return asDto(votesRepository.findAll());
    }

    @GetMapping("/date")
    public List<VotesDto> getVotesByDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get votesByDate {}  user {}", date, authId());
        return asDto(votesRepository.getAllByDate(date));
    }

    @GetMapping("/dateBetween")
    public List<VotesDto> getVotesBetweenDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("get votes between {} and {}", startDate, endDate);
        return asDto(votesRepository.getAllByDateBetweenOrderByDate(startDate, endDate));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Votes> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int id = authUser.id();
        log.info("create votes for user {}", id);
        Votes created = votesRepository.save(new Votes(null, LocalDate.now(), authUser.getUser(), restaurantRepository.getById(restaurantId)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int id = authUser.id();
        log.info("update votes {} for user {}", restaurantId, id);
        Votes newVotes = votesRepository.getVotesByUserIdAndDate(id, LocalDate.now())
                .orElseThrow(() -> new NotFoundException("Vote not found"));
        votesRepository.save(new Votes(newVotes.id(), LocalDate.now(), authUser.getUser(), restaurantRepository.getById(restaurantId)));
    }
}
