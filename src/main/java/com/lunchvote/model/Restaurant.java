package com.lunchvote.model;

public class Restaurant extends AbstractNamedEntity {

   public Restaurant() {
   }

   @Override
   public String toString() {
      return "Restaurant{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }
}
