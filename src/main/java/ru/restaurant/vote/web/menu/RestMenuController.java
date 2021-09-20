package ru.restaurant.vote.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.vote.repository.MenuRepository;
import ru.restaurant.vote.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant.vote.util.MenuUtil.asTo;
import static ru.restaurant.vote.util.MenuUtil.menuAsTo;
import static ru.restaurant.vote.web.SecurityUtil.authId;
import static ru.restaurant.vote.web.URLPattern.URL;

@RestController
@RequestMapping(value = RestMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestMenuController {
    public static final String REST_URL = URL + "/menu";

    private final MenuRepository menuRepository;

    @GetMapping("/today")
    public List<MenuTo> getTodayMenu() {
        log.info("get today Menu");
        return asTo(menuRepository.getAllByDate(LocalDate.now()));
    }

    @GetMapping("/{id}")
    public MenuTo get(@PathVariable int id) {
        log.info("get menuById {} ", id);
        return menuAsTo(menuRepository.getById(id));
    }

    @GetMapping("/date")
    public List<MenuTo> getMenuByDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get menuByDate {}  user {}", date, authId());
        return asTo(menuRepository.getAllByDate(date));
    }

    @GetMapping("/dateBetween")
    public List<MenuTo> getMenuBetweenDate(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("get menu between {} and {}", startDate, endDate);
        return asTo(menuRepository.getAllByDateBetweenOrderByDate(startDate, endDate));
    }

    @GetMapping
    public List<MenuTo> getAll() {
        log.info("get all menu");
        return asTo(menuRepository.findAll());
    }
}
