package ru.restaurant.vote.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.restaurant.vote.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class VotesDto extends BaseTo {
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDate date;

    @NotNull
    private UserTo userTo;

    @NotNull
    private Restaurant restaurant;

    public VotesDto(Integer id, LocalDate date, UserTo userTo, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.userTo = userTo;
        this.restaurant = restaurant;
    }
}
