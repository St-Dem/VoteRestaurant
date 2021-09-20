package ru.restaurant.vote.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_unique_name_idx")})
public class Restaurant extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Votes> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Menu> menu;

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress());
    }

    public Restaurant(Integer id, String name, @NotBlank @Size(min = 1, max = 100) String address) {
        super(id, name);
        this.address = address;
    }

    public Restaurant(Integer id, String name, String address, List<Votes> votes, List<Menu> menu) {
        super(id, name);
        this.address = address;
        this.votes = votes;
        this.menu = menu;
    }
}
