package com.lunchvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

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
