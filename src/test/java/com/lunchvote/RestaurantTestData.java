package com.lunchvote;

import com.lunchvote.model.Restaurant;

import static com.lunchvote.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator();

    public static final int NOT_FOUND = 10;

    public static final Restaurant kfcRest = new Restaurant(START_SEQ + 5,"KFC");
    public static final Restaurant zimaRest = new Restaurant(START_SEQ + 6, "Zima");
    public static final Restaurant fiveGuysRest = new Restaurant(START_SEQ + 7, "Five Guys");
    public static final Restaurant mcDonaldRest = new Restaurant(START_SEQ + 8, "McDonald");

    public static Restaurant getNew(){
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated(){
        Restaurant updated = new Restaurant(kfcRest);
        updated.setName("Updated KFC");
        return updated;
    }
}
