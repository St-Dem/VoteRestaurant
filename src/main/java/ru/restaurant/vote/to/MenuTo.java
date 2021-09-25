package ru.restaurant.vote.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.restaurant.vote.model.Dish;
import ru.restaurant.vote.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class MenuTo extends BaseTo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Restaurant restaurant;

    @NotNull
    private LocalDate date;

    @NotNull
    private List<Dish> dish;

    public MenuTo(Integer id, @NotNull Restaurant restaurant, @NotNull LocalDate date, List<Dish> dish) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dish = dish;
    }
}
