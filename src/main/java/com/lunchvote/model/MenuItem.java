package com.lunchvote.model;

import java.time.LocalDate;

public class MenuItem extends AbstractNamedEntity {

    private Restaurant restaurant;
    private int price;
    private LocalDate created;

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
