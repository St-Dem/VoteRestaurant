package ru.restaurant.vote.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = {"restaurant", "dish"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(name = Menu.RESTAURANT_AND_DISH,
        attributeNodes = {@NamedAttributeNode("restaurant"), @NamedAttributeNode("dish")})
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "created", "dish_id"}, name = "menu_unique_datetime_dish_idx")})
public class Menu extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String RESTAURANT_AND_DISH = "Menu.restaurantDish";

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "created", nullable = false, columnDefinition = "date default now()")
    private LocalDate date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    public Menu(Integer id, Restaurant restaurant, LocalDate localDate, Dish dish, int price) {
        super(id);
        this.restaurant = restaurant;
        this.date = localDate;
        this.price = price;
        this.dish = dish;
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getRestaurant(), menu.getDate(), menu.getDish(), menu.getPrice());
    }
}
