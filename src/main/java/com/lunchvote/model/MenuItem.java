package com.lunchvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_item")
@NamedQueries({
        @NamedQuery(name = MenuItem.DELETE, query = "DELETE FROM MenuItem m WHERE m.id=:id"),
        @NamedQuery(name = MenuItem.BY_RESTAURANT, query = "SELECT m FROM MenuItem m WHERE m.restaurant.id=:restaurantId ORDER BY m.created DESC"),
        @NamedQuery(name = MenuItem.BY_RESTAURANT_AND_DATE, query = "SELECT m FROM MenuItem m WHERE m.restaurant.id=:restaurantId AND m.created=:date ORDER BY m.created DESC")
})
public class MenuItem extends AbstractNamedEntity {

    public static final String DELETE = "MenuItem.delete";
    public static final String BY_RESTAURANT = "MenuItem.getByRestaurant";
    public static final String BY_RESTAURANT_AND_DATE = "MenuItem.getByRestaurantAndDate";

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
