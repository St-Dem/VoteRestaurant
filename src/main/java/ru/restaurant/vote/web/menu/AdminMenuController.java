package ru.restaurant.vote.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.vote.model.Menu;
import ru.restaurant.vote.repository.MenuRepository;
import ru.restaurant.vote.to.MenuTo;

import javax.validation.Valid;
import java.net.URI;

import static ru.restaurant.vote.util.MenuUtil.menuToAsMenu;
import static ru.restaurant.vote.util.validation.ValidationUtil.assureIdConsistent;
import static ru.restaurant.vote.util.validation.ValidationUtil.checkNew;
import static ru.restaurant.vote.web.SecurityUtil.authId;
import static ru.restaurant.vote.web.URLPattern.ADMIN_URL;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {
    public static final String REST_URL = ADMIN_URL + "/menu";

    private final MenuRepository menuRepository;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete menu {} user {}", id, authId());
        menuRepository.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody MenuTo menuTo) {
        log.info("create {} user {}", menuTo, authId());
        checkNew(menuTo);
        Menu created = menuRepository.save(menuToAsMenu(menuTo));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MenuTo menuTo, @PathVariable int id) {
        log.info("update {} with id {} user {}", menuTo, id, authId());
        assureIdConsistent(menuTo, id);
        menuRepository.save(menuToAsMenu(menuTo));
    }
}
