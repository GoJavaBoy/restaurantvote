package com.lunchvote.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
})
public class Restaurant extends AbstractNamedEntity {

   public static final String DELETE = "Restaurant.delete";
   public static final String ALL_SORTED = "Restaurant.getAllSorted";

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
