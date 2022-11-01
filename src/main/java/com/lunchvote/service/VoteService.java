package com.lunchvote.service;

import com.lunchvote.model.Vote;
import com.lunchvote.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvote.util.ValidationUtil.checkNotFound;
import static com.lunchvote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote createOrUpdate(int restaurantId, int userId) {
        return repository.save(restaurantId, userId);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Vote> getByUser(int userId) {
        return repository.getByUser(userId);
    }

    public Vote getByUserAndDate(int userId, LocalDate date) {
        return checkNotFound(repository.getByUserAndDate(userId, date), "userId=" + userId + " date=" + date);
    }

    public List<Vote> getByRestaurant(int restaurantId) {
        return repository.getByRestaurant(restaurantId);
    }

    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date) {
        return repository.getByRestaurantAndDate(restaurantId, date);
    }
}
