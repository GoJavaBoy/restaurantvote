package com.lunchvote.model;

import java.time.LocalDate;

public class Vote extends AbstractBaseEntity {

    private Restaurant restaurant;
    private User user;
    private LocalDate created;

    public Vote() {
    }

    public Vote(Restaurant restaurant, User user, LocalDate created) {
        this(null, restaurant, user, created);
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
