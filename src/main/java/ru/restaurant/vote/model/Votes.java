package ru.restaurant.vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"user", "restaurant"})
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "created"}, name = "votes_unique_user_date_idx")})
@NamedEntityGraph(name = Votes.RESTAURANT,  attributeNodes = @NamedAttributeNode("restaurant"))
public class Votes extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String RESTAURANT = "Votes.restaurant";
    @NotNull
    @Column(name = "created", nullable = false, columnDefinition = "date default now()")
    private LocalDate date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private User user;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;


    public Votes(Votes votes) {
        this(votes.getId(), votes.getDate(), votes.getUser(), votes.getRestaurant());
    }

    public Votes(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }
}
