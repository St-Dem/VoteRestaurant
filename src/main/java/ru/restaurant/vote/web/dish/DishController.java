package ru.restaurant.vote.web.dish;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.vote.model.Dish;
import ru.restaurant.vote.repository.DishRepository;
import ru.restaurant.vote.to.DishTo;

import javax.validation.Valid;
import java.util.List;

import static ru.restaurant.vote.util.CreateUtil.create;
import static ru.restaurant.vote.util.validation.ValidationUtil.assureIdConsistent;
import static ru.restaurant.vote.web.SecurityUtil.authId;
import static ru.restaurant.vote.web.URLPattern.ADMIN_URL;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "dishes")
public class DishController extends AbstractDishController{
    public static final String REST_URL = ADMIN_URL + "/dishes";

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dishById {}", id);
        return dishRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish {} user {}", id, authId());
        dishRepository.delete(id);
    }

    @GetMapping
    public List<Dish> getAll() {
        log.info("get all dishes ");
        return dishRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        Dish dish = fromTo(dishTo);
        log.info("create {} user {}", dish, authId());
        return create(dish, REST_URL, dishRepository::save);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        Dish dish = fromTo(dishTo);
        log.info("update {} with id={} user {}", dish, id, authId());
        assureIdConsistent(dish, id);
        dishRepository.save(dish);
    }
}
