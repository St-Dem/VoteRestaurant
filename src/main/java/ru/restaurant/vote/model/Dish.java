package ru.restaurant.vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@ToString(callSuper = true, exclude = "menu")
@NamedEntityGraph(name = Dish.MENU_DISH,
        attributeNodes = @NamedAttributeNode("menu"))
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "menu_id"}, name = "dish_unique_name_menu_idx")})
public class Dish extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String MENU_DISH = "Dish.menu";

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Dish(Integer id, String name, int price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getMenu());
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }
}
