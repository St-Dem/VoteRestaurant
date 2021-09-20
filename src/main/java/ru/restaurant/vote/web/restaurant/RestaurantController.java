package ru.restaurant.vote.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.vote.model.Restaurant;
import ru.restaurant.vote.repository.RestaurantRepository;

import javax.validation.Valid;
import java.util.List;

import static ru.restaurant.vote.util.CreateUtil.create;
import static ru.restaurant.vote.util.validation.ValidationUtil.assureIdConsistent;
import static ru.restaurant.vote.web.SecurityUtil.authId;
import static ru.restaurant.vote.web.URLPattern.ADMIN_URL;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestaurantController {
    public static final String REST_URL = ADMIN_URL + "/restaurant";

    private final RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get dishById {} ", id);
        return restaurantRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish {} user {}", id, authId());
        restaurantRepository.delete(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all dishes");
        return restaurantRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {} user {}", restaurant, authId());
        return create(restaurant, REST_URL, restaurantRepository::save);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id={} user {}", restaurant, id, authId());
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }
}
