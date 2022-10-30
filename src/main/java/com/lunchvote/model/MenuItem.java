package com.lunchvote.model;

import java.time.LocalDate;

public class MenuItem extends AbstractNamedEntity {

    private Restaurant restaurant;
    private int price;
    private LocalDate created;

    public MenuItem() {
    }

    public MenuItem(String name, Restaurant restaurant, int price, LocalDate created) {
        this(null, name, restaurant, price, created);
    }

    public MenuItem(Integer id, String name, Restaurant restaurant, int price, LocalDate created) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.created = created;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dateTime=" + created +
                '}';
    }
}
