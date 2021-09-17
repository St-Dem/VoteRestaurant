package ru.restaurant.vote.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private int userId;

    @NotNull
    private int restaurantId;

    public VotesTo(int userId, LocalDate date) {
        this(null, date);
    }

    public VotesTo(Integer id, LocalDate date, int userId, int restaurantId) {
        this(id, date);
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public VotesTo(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }
}
