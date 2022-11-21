package com.lunchvote;

import com.lunchvote.model.MenuItem;

import java.time.LocalDate;

import static com.lunchvote.RestaurantTestData.kfcRest;
import static com.lunchvote.model.AbstractBaseEntity.START_SEQ;

public class MenuItemTestData {

    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("restaurant");

    public static final int NOT_FOUND = 10;

    public static final MenuItem kfcBurger = new MenuItem(START_SEQ + 9, "Kfc Burger", kfcRest, 300, LocalDate.now());
    public static final MenuItem kfcShake = new MenuItem(START_SEQ + 10, "Kfc Shake", kfcRest, 250, LocalDate.now());
    public static final MenuItem kfcFries = new MenuItem(START_SEQ + 11, "Kfc Fries", kfcRest, 110, LocalDate.now());
    public static final MenuItem kfcNuggets = new MenuItem(START_SEQ + 12, "Kfc Nuggets", kfcRest, 550, LocalDate.now());

    public static MenuItem getNew() {
        return new MenuItem("New Menu Item", kfcRest, 350);
    }

    public static MenuItem getUpdated() {
        MenuItem updated = new MenuItem(kfcBurger);
        updated.setPrice(123);
        updated.setName("New Updated Name");
        return updated;
    }
}
