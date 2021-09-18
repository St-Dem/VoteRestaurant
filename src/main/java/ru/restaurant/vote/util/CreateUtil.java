package ru.restaurant.vote.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.vote.model.BaseEntity;

import java.net.URI;
import java.util.function.Function;

import static ru.restaurant.vote.util.validation.ValidationUtil.checkNew;

public class CreateUtil {

    public static <T extends BaseEntity> ResponseEntity<T> create(T entity, String path, Function<T, T> save) {
        checkNew(entity);
        T created = save.apply(entity);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
