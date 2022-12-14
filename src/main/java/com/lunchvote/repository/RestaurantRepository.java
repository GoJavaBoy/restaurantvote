package com.lunchvote.repository;

import com.lunchvote.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository repository;

    public RestaurantRepository(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant save(Restaurant restaurant) {
       return repository.save(restaurant);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }
}
