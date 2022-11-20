package com.lunchvote;

import com.lunchvote.model.Vote;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VoteTestUtil {

    public static void isEqual(Vote expected, Vote actual) {
        int restaurantId = expected.getRestaurant().getId();
        int userId = expected.getUser().getId();
        assertEquals(restaurantId, (int) actual.getRestaurant().getId());
        assertEquals(userId, (int) actual.getUser().getId());
    }

    public static void isEqual(List<Vote> expected, List<Vote> actual) {
        if (expected.size() != actual.size()) {
            throw new AssertionError("List sizes are not same");
        }
        Iterator<Vote> itExp = expected.iterator();
        Iterator<Vote> itAct = actual.iterator();
        while (itExp.hasNext()) {
            Vote exp = itExp.next();
            Vote act = itAct.next();
            isEqual(exp, act);
            assertEquals(exp.getCreated(), act.getCreated());
        }
    }
}
