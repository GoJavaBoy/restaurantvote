package com.lunchvote.repository;

import com.lunchvote.model.Vote;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    List<Vote> findAllByUserId(int userId, Sort sort);

    Optional<Vote> getByUserIdAndCreated(int userId, LocalDate created);

    List<Vote> getByRestaurantId(int restaurantId, Sort sort);

    List<Vote> getByRestaurantIdAndCreated(int restaurantId, LocalDate created, Sort sort);
}
