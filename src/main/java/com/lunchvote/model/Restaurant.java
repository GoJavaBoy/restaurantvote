package com.lunchvote.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

   public Restaurant() {
   }

   public Restaurant(Restaurant restaurant) {
      this(restaurant.id, restaurant.name);
   }

   public Restaurant(String name) {
      this(null, name);
   }

   public Restaurant(Integer id, String name) {
      super(id, name);
   }

   @Override
   public String toString() {
      return "Restaurant{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }
}
