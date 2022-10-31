package com.lunchvote.repository;

import com.lunchvote.model.MenuItem;
import com.lunchvote.model.Vote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MenuItemRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public MenuItem save(MenuItem menuItem){
        if (menuItem.isNew()){
            em.persist(menuItem);
            return menuItem;
        } else {
            return em.merge(menuItem);
        }
    };

    @Transactional
    public boolean delete(int id){
        return em.createNamedQuery(MenuItem.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    };

    public MenuItem get(int id){
        return em.find(MenuItem.class, id);
    };

    public List<MenuItem> getByRestaurant(int restaurantId){
        return em.createNamedQuery(MenuItem.BY_RESTAURANT, MenuItem.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    };

    public List<MenuItem> getByRestaurantAndDate(int restaurantId, LocalDate date){
        return em.createNamedQuery(MenuItem.BY_RESTAURANT_AND_DATE, MenuItem.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("created", date)
                .getResultList();
    };
}
