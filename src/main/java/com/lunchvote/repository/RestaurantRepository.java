package com.lunchvote.repository;

import com.lunchvote.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class)
                .getResultList();
    }
}
