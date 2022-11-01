package com.lunchvote.repository;

import com.lunchvote.model.Restaurant;
import com.lunchvote.model.User;
import com.lunchvote.model.Vote;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Vote save(int restaurantId, int userId) {
        Vote todayVote = getByUserAndDate(userId, LocalDate.now());
        Restaurant restaurantRef = em.getReference(Restaurant.class, restaurantId);

        if (todayVote != null) {
            if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
                todayVote.setRestaurant(restaurantRef);
                return em.merge(todayVote);
            }
            return todayVote;
        } else {
            User userRef = em.getReference(User.class, userId);
            Vote vote = new Vote(restaurantRef, userRef);
            em.persist(vote);
            return vote;
        }
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    public List<Vote> getByUser(int userId) {
        return em.createNamedQuery(Vote.BY_USER, Vote.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public Vote getByUserAndDate(int userId, LocalDate date) {
        List<Vote> votes = em.createNamedQuery(Vote.BY_USER_AND_DATE, Vote.class)
                .setParameter("userId", userId)
                .setParameter("created", date)
                .getResultList();
        return DataAccessUtils.singleResult(votes);
    }

    public List<Vote> getByRestaurant(int restaurantId) {
        return em.createNamedQuery(Vote.BY_RESTAURANT, Vote.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date) {
        return em.createNamedQuery(Vote.BY_RESTAURANT_AND_DATE, Vote.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("created", date)
                .getResultList();
    }
}