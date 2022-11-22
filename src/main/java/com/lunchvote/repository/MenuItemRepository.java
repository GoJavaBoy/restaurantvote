package com.lunchvote.repository;

import com.lunchvote.model.MenuItem;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuItemRepository {
    private static final Sort SORT_CREATED = Sort.by(Sort.Direction.ASC, "created");

    private final CrudMenuItemRepository menuItemRepository;

    public MenuItemRepository(CrudMenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem save(MenuItem menuItem){
        return menuItemRepository.save(menuItem);
    };

    public boolean delete(int id){
        return menuItemRepository.delete(id) != 0;
    };

    public MenuItem get(int id){
        return menuItemRepository.findById(id).orElse(null);
    };

    public List<MenuItem> getByRestaurant(int restaurantId){
        return menuItemRepository.getAllByRestaurantId(restaurantId, SORT_CREATED);
    };

    public List<MenuItem> getByRestaurantAndDate(int restaurantId, LocalDate date){
        return menuItemRepository.getAllByRestaurantIdAndCreated(restaurantId, date, SORT_CREATED);
    };
}
