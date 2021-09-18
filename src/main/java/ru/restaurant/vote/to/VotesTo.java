package ru.restaurant.vote.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.restaurant.vote.model.Restaurant;
import ru.restaurant.vote.model.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class VotesTo extends BaseTo {
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDate date;

    @NotNull
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
