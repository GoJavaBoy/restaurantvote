package com.lunchvote.repository;

import com.lunchvote.model.Restaurant;
import com.lunchvote.model.User;
import com.lunchvote.model.Vote;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class VoteRepository {
    private static final Sort SORT_CREATED = Sort.by(Sort.Direction.ASC, "created");

    private final Clock clock;

    private final CrudVoteRepository voteRepository;
    private final CrudUserRepository userRepository;
    private final CrudRestaurantRepository restaurantRepository;

    public VoteRepository(CrudVoteRepository voteRepository, CrudUserRepository userRepository, CrudRestaurantRepository restaurantRepository, Clock clock) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.clock = clock;
    }

    public Vote save(int restaurantId, int userId) {
        Vote todayVote = getByUserAndDate(userId, LocalDate.now());
        Restaurant restaurantRef = restaurantRepository.getReferenceById(restaurantId);
        if (todayVote != null) {
            if (LocalTime.now(clock).isBefore(LocalTime.of(11, 0))) {
                todayVote.setRestaurant(restaurantRef);
                return voteRepository.save(todayVote);
            }
            return todayVote;
        } else {
            User userRef = userRepository.getReferenceById(userId);
            Vote vote = new Vote(restaurantRef, userRef);
            return voteRepository.save(vote);
        }
    }

    public boolean delete(int id) {
        return voteRepository.delete(id) != 0;
    }

    public Vote get(int id) {
        return voteRepository.findById(id).orElse(null);
    }

    public List<Vote> getByUser(int userId) {
        return voteRepository.findAllByUserId(userId, SORT_CREATED);
    }

    public Vote getByUserAndDate(int userId, LocalDate date) {
//        List<Vote> votes = em.createNamedQuery(Vote.BY_USER_AND_DATE, Vote.class)
//                .setParameter("userId", userId)
//                .setParameter("created", date)
//                .getResultList();
//        return DataAccessUtils.singleResult(votes);
        return voteRepository.getByUserIdAndCreated(userId, date).orElse(null);
    }

    public List<Vote> getByRestaurant(int restaurantId) {
        return voteRepository.getByRestaurantId(restaurantId, SORT_CREATED);
    }

    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date) {
        return voteRepository.getByRestaurantIdAndCreated(restaurantId, date, SORT_CREATED);
    }
}
