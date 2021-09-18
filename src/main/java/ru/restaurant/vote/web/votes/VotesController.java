package ru.restaurant.vote.web.votes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.vote.model.Votes;
import ru.restaurant.vote.repository.VotesRepository;
import ru.restaurant.vote.util.validation.InvalidTime;
import ru.restaurant.vote.web.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

import static ru.restaurant.vote.util.CreateUtil.create;
import static ru.restaurant.vote.util.InvalidTimeUtil.beforeInvalidTime;
import static ru.restaurant.vote.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = VotesController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VotesController {
    public static final String REST_URL = "/api/votes";

    private final VotesRepository votesRepository;

    @GetMapping("/{id}")
    public Votes get(@PathVariable int id) {
        log.info("get votesById {} ", id);
        return votesRepository.getVotesByUserIdAndId(SecurityUtil.authId(), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete votes {} ", id);
        votesRepository.delete(id);
    }

    @GetMapping
    public List<Votes> getAll() {
        log.info("get all votes");
        return votesRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Votes> createWithLocation(@Valid @RequestBody Votes votes) {
        log.info("create {}", votes);
        return create(votes, REST_URL, votesRepository::save);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Votes votes, @PathVariable int id) {
        log.info("update {} with id={}", votes, id);
        assureIdConsistent(votes, id);
        if (beforeInvalidTime()) {
            votesRepository.save(votes);
        } else {
            throw new InvalidTime("you too late");
        }
    }
}
