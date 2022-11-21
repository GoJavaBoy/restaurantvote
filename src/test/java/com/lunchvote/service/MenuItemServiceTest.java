package com.lunchvote.service;

import com.lunchvote.model.MenuItem;
import com.lunchvote.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static com.lunchvote.MenuItemTestData.*;
import static com.lunchvote.RestaurantTestData.kfcRest;
import static com.lunchvote.TestUtil.isEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MenuItemServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuItemService service;

    @Test
    public void create() {
        MenuItem menuItem = service.create(getNew());
        int newId = menuItem.getId();
        MenuItem newWithId = getNew();
        newWithId.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(menuItem, newWithId);
        MENU_ITEM_MATCHER.assertMatch(service.get(newId), newWithId);
        assertEquals(menuItem.getRestaurant().getId(), newWithId.getRestaurant().getId());
    }

    @Test
    public void delete() {
        service.delete(kfcBurger.getId());
        assertThrows(NotFoundException.class, () -> service.get(kfcBurger.getId()));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        MenuItem menuItem = service.get(kfcBurger.getId());
        MENU_ITEM_MATCHER.assertMatch(menuItem, kfcBurger);
        assertEquals(menuItem.getRestaurant().getId(), kfcBurger.getRestaurant().getId());
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void update() {
        MenuItem updated = getUpdated();
        service.update(updated);
        MENU_ITEM_MATCHER.assertMatch(service.get(updated.getId()), getUpdated());
    }

    @Test
    public void getByRestaurant() {
        List<MenuItem> menuItems = service.getByRestaurant(kfcRest.getId());
        isEqual(menuItems, List.of(kfcBurger, kfcShake, kfcFries, kfcNuggets), MENU_ITEM_MATCHER::assertMatch);
    }

    @Test
    public void getByRestaurantAndDate() {
        List<MenuItem> menuItems = service.getByRestaurantAndDate(kfcRest.getId(), LocalDate.now());
        isEqual(menuItems, List.of(kfcBurger, kfcShake, kfcFries, kfcNuggets), MENU_ITEM_MATCHER::assertMatch);
    }

    @Test
    public void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new MenuItem(null, "a", kfcRest, 100, LocalDate.now())));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new MenuItem(null, "New Kfc Item", null, 100, LocalDate.now())));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new MenuItem(null, "Super Nullable Item", kfcRest, 100, null)));
    }
}