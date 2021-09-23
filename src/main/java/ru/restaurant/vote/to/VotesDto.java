package ru.restaurant.vote.to;

import lombok.*;
import ru.restaurant.vote.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VotesDto extends BaseTo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDate date;

    @NotNull
    private UserDto userDto;

    @NotNull
    private Restaurant restaurant;

    public VotesDto(Integer id, LocalDate date, UserDto userDto, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.userDto = userDto;
        this.restaurant = restaurant;
    }
}
