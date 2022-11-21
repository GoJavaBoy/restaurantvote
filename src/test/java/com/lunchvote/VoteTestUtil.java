package com.lunchvote;

import com.lunchvote.model.Vote;

import static org.junit.Assert.assertEquals;

public class VoteTestUtil {

    public static void isEqual(Vote expected, Vote actual) {
        int restaurantId = expected.getRestaurant().getId();
        int userId = expected.getUser().getId();
        assertEquals(restaurantId, (int) actual.getRestaurant().getId());
        assertEquals(userId, (int) actual.getUser().getId());
    }
}
