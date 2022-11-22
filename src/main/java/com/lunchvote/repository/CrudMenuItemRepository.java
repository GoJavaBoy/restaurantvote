package com.lunchvote.repository;

import com.lunchvote.model.MenuItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem i WHERE i.id=:id")
    int delete(@Param("id") int id);

    List<MenuItem> getAllByRestaurantId(int restaurantId, Sort sort);

    List<MenuItem> getAllByRestaurantIdAndCreated(int restaurantId, LocalDate created, Sort sort);
}
