package com.lunchvote.model;

import java.time.LocalDate;

public class Vote extends AbstractBaseEntity {

    private Restaurant restaurant;
    private User user;
    private LocalDate created;

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

    public LocalDate getDateTime() {
        return created;
    }

    public void setDateTime(LocalDate date) {
        this.created = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", dateTime=" + created +
                '}';
    }
}
