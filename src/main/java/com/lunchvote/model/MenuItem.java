package com.lunchvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "created", columnDefinition = "date", nullable = false)
    @NotNull
    private LocalDate created;

    public MenuItem() {
    }

    public MenuItem(MenuItem menuItem) {
        this(menuItem.id, menuItem.name, menuItem.restaurant, menuItem.price, menuItem.created);
    }

    public MenuItem(String name, Restaurant restaurant, int price) {
        this(null, name, restaurant, price, LocalDate.now());
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
