package com.lunchvote.service;

import com.lunchvote.MockClock;
import com.lunchvote.model.Vote;
import com.lunchvote.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvote.RestaurantTestData.fiveGuysRest;
import static com.lunchvote.RestaurantTestData.kfcRest;
import static com.lunchvote.UserTestData.NOT_FOUND;
import static com.lunchvote.UserTestData.user;
import static com.lunchvote.VoteTestData.*;
import static com.lunchvote.VoteTestUtil.isEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ContextConfiguration("classpath:spring/spring-mockTime.xml")
public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Autowired
    private MockClock clock;

    @Before
    public void fixTime() {
        clock.fixToNormalTime();
    }

    @Test
    public void create() {
        Vote vote = service.createOrUpdate(kfcRest.getId(), user.getId());
        assertEquals(vote.getCreated(), LocalDate.now());
        isEqual(vote, userVote);
    }

    @Test
    public void updateBeforeEleven() {
        clock.setBeforeEleven();
        service.createOrUpdate(kfcRest.getId(), user.getId());
        Vote updated = service.createOrUpdate(fiveGuysRest.getId(), user.getId());
        assertEquals(updated.getCreated(), LocalDate.now());
        isEqual(updated, getUpdated());
    }

    @Test
    public void updateAfterEleven() {
        clock.setAfterEleven();
        service.createOrUpdate(kfcRest.getId(), user.getId());
        Vote updated = service.createOrUpdate(fiveGuysRest.getId(), user.getId());
        assertEquals(updated.getCreated(), LocalDate.now());
        assertThrows(AssertionError.class, () -> isEqual(updated, getUpdated()));
        isEqual(service.get(userVote.getId()), userVote);
    }

    @Test
    public void delete() {
        service.delete(userVote.getId());
        assertThrows(NotFoundException.class, () -> service.get(userVote.getId()));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        Vote vote = service.get(adminVote.id());
        assertEquals(vote.getCreated(), adminVote.getCreated());
        isEqual(vote, adminVote);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getByUser() {
        List<Vote> votes = service.getByUser(user.getId());
        isEqual(votes, List.of(userVote3, userVote2, userVote));
    }

    @Test
    public void getByUserAndDate() {
        Vote vote = service.getByUserAndDate(user.getId(), LocalDate.of(2022, 12, 2));
        isEqual(vote, userVote2);
    }

    @Test
    public void getByRestaurant() {
        List<Vote> votes = service.getByRestaurant(kfcRest.getId());
        isEqual(votes, List.of(userVote, user2Vote, user3Vote));
    }

    @Test
    public void getByRestaurantAndDate() {
        List<Vote> votes = service.getByRestaurantAndDate(kfcRest.getId(), LocalDate.of(2022, 11, 1));
        isEqual(votes, List.of(userVote, user2Vote, user3Vote));
    }
}