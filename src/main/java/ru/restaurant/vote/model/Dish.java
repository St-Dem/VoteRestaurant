package ru.restaurant.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "dish_unique_name_idx")})
public class Dish extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    @JsonIgnore
    private List<Menu> menu;

    public Dish(Integer id, String name) {
        super(id, name);
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName());
    }
}
