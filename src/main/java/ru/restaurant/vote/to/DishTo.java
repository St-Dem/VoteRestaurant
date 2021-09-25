package ru.restaurant.vote.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class DishTo extends NamedTo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @PositiveOrZero
    int price;

    @PositiveOrZero
    int menuId;

    public DishTo(Integer id, String name, int price, int menuId) {
        super(id, name);
        this.name = name;
        this.price = price;
        this.menuId = menuId;
    }

    public DishTo(String name, int price, int menuId) {
        this(null, name, price, menuId);
    }
}
