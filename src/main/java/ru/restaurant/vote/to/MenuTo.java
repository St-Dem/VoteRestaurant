package ru.restaurant.vote.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.restaurant.vote.HasId;
import ru.restaurant.vote.model.Dish;
import ru.restaurant.vote.model.Restaurant;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class MenuTo extends BaseTo{
    @NotNull
    private Restaurant restaurant;

    @NotNull
    private LocalDate date;

    @NotNull
    private Dish dish;

    @NotNull
    private Integer price;

    public MenuTo(@NotNull Restaurant restaurant, @NotNull LocalDate date, @NotNull Dish dish, @NotNull Integer price) {
        this.restaurant = restaurant;
        this.date = date;
        this.dish = dish;
        this.price = price;
    }
}
