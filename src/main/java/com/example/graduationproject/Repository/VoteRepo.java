package com.example.graduationproject.Repository;

import com.example.graduationproject.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepo extends JpaRepository<Vote, Integer> {

    Vote findByUserIdAndDateVote(Integer user_id, LocalDate dateVote);

    List<Vote> findAllByRestaurantIdAndAndDateVote(Integer restaurantId, LocalDate date);
}