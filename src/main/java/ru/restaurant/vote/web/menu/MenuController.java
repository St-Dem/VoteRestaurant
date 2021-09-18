package ru.restaurant.vote.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.vote.model.Menu;
import ru.restaurant.vote.repository.MenuRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.util.CreateUtil.create;
import static ru.restaurant.vote.util.validation.ValidationUtil.assureIdConsistent;
import static ru.restaurant.vote.web.SecurityUtil.authId;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MenuController {
    public static final String REST_URL = "/api/menu";

    private final MenuRepository menuRepository;

    @GetMapping("/today")
    public List<Menu> getTodayMenu() {
        log.info("get today Menu");
        return menuRepository.getAllByDate(LocalDate.now());
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        log.info("get menuById {} ", id);
        return menuRepository.getMenuById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete menu {} user {}", id, authId());
        menuRepository.delete(id);
    }

    @GetMapping
    public List<Menu> getAll() {
        log.info("get all menu");
        return menuRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu) {
        log.info("create {} user {}", menu, authId());
        return create(menu, REST_URL, menuRepository::save);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id) {
        log.info("update {} with id={} user {}", menu, id, authId());
        assureIdConsistent(menu, id);
        menuRepository.save(menu);
    }
}
