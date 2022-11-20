package com.lunchvote;

import com.lunchvote.model.Vote;

import java.time.LocalDate;

import static com.lunchvote.RestaurantTestData.*;
import static com.lunchvote.UserTestData.*;
import static com.lunchvote.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("restaurant", "user");

    public static final Vote adminVote = new Vote(START_SEQ + 28, mcDonaldRest, admin, LocalDate.of(2022, 11, 1));
    public static final Vote user3Vote = new Vote(START_SEQ + 27, kfcRest, user3, LocalDate.of(2022, 11, 1));
    public static final Vote user2Vote = new Vote(START_SEQ + 26, kfcRest, user2, LocalDate.of(2022, 11, 1));
    public static final Vote userVote = new Vote(START_SEQ + 25, kfcRest, user, LocalDate.of(2022, 11, 1));
    public static final Vote userVote2 = new Vote(START_SEQ + 29, fiveGuysRest, user, LocalDate.of(2022, 12, 2));
    public static final Vote userVote3 = new Vote(START_SEQ + 30, mcDonaldRest, user, LocalDate.of(2022, 12, 3));

//    public static Vote getNew(){
//        Vote actual = new Vote(userVote);
//        actual.setCreated(LocalDate.now());
//        actual.setRestaurant(zimaRest);
//
//    }

    public static Vote getUpdated() {
        Vote vote = new Vote(userVote);
        vote.setCreated(LocalDate.now());
        vote.setRestaurant(fiveGuysRest);
        return vote;
    }
}


