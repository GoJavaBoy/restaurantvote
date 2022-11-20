package com.lunchvote.service;

import com.lunchvote.model.MenuItem;
import com.lunchvote.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem create(MenuItem menuItem){
        Assert.notNull(menuItem, "menuItem must not be null");
        return repository.save(menuItem);
    }

    public void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }

    public MenuItem get(int id){
       return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(MenuItem menuItem){
        Assert.notNull(menuItem, "menuItem must not be null");
        checkNotFoundWithId(repository.save(menuItem), menuItem.id());
    }

    public List<MenuItem> getByRestaurant(int restaurantId){
        return repository.getByRestaurant(restaurantId);
    }

    public List<MenuItem> getByRestaurantAndDate(int restaurantId, LocalDate date){
        Assert.notNull(date, "date must not be null");
        return repository.getByRestaurantAndDate(restaurantId, date);
    }
}
