package com.lunchvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.created DESC"),
        @NamedQuery(name = Vote.BY_USER_AND_DATE, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.created=:created"),
        @NamedQuery(name = Vote.BY_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId ORDER BY v.created DESC"),
        @NamedQuery(name = Vote.BY_RESTAURANT_AND_DATE, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.created=:created ORDER BY v.created DESC"),
})
public class Vote extends AbstractBaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String BY_USER_AND_DATE = "Vote.byUserAndDate";
    public static final String BY_USER = "Vote.byUser";
    public static final String BY_RESTAURANT = "Vote.byRestaurant";
    public static final String BY_RESTAURANT_AND_DATE = "Vote.byRestaurantAndDate";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "created", columnDefinition = "date", nullable = false)
    @NotNull
    private LocalDate created;

    public Vote() {
    }

    public Vote(Restaurant restaurant, User user) {
        this(null, restaurant, user, LocalDate.now());
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getRestaurant(), vote.getUser(), vote.getCreated());
    }

    public Vote(Integer id, Restaurant restaurant, User user, LocalDate created) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.created = created;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", dateTime=" + created +
                '}';
    }
}
