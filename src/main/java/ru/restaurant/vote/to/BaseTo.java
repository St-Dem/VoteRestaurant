package ru.restaurant.vote.to;

import lombok.*;
import ru.restaurant.vote.HasId;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public abstract class BaseTo implements HasId {
    protected Integer id;
}
