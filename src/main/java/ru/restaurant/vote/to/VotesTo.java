package ru.restaurant.vote.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.restaurant.vote.model.Restaurant;
import ru.restaurant.vote.model.User;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VotesTo extends BaseTo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDate date;

    @NotNull
    @JsonIgnore
    private User user;

    @NotNull
    private Restaurant restaurant;

    public VotesTo(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }
}
