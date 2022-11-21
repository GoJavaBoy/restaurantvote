package com.lunchvote.service;

import com.lunchvote.TestUtil;
import com.lunchvote.model.Restaurant;
import com.lunchvote.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.lunchvote.RestaurantTestData.*;
import static org.junit.Assert.assertThrows;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void create() {
        Restaurant created = service.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void get() {
        RESTAURANT_MATCHER.assertMatch(service.get(kfcRest.getId()), kfcRest);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(updated.getId()), getUpdated());
    }

    @Test
    public void delete() {
        service.delete(kfcRest.getId());
        assertThrows(NotFoundException.class, () -> service.get(kfcRest.getId()));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void getAll() {
        List<Restaurant> restaurants = service.getAll();
        TestUtil.isEqual(restaurants, List.of(fiveGuysRest, kfcRest, mcDonaldRest, zimaRest), RESTAURANT_MATCHER::assertMatch);
    }

    @Test
    public void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "")));
    }
}