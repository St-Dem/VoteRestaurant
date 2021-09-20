package ru.restaurant.vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = {"restaurant", "dish"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"created", "restaurant_id"}, name = "menu_unique_restaurant_date_idx")})
public class Menu extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonBackReference
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "created", nullable = false, columnDefinition = "date default now()")
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> dish;

    public Menu(Integer id, Restaurant restaurant, LocalDate localDate, Dish ...dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = localDate;
        this.dish = Arrays.asList(dishes);
    }

    public Menu(Menu menu) {
        super(menu.id);
        this.restaurant = menu.getRestaurant();
        this.date = menu.getDate();
        this.dish = menu.getDish();
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate localDate, List<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = localDate;
        this.dish = dishes;
    }
}
