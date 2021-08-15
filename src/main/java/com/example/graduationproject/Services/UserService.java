package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Entity.User;
import com.example.graduationproject.Entity.Vote;
import com.example.graduationproject.Repository.RestaurantRepo;
import com.example.graduationproject.Repository.UserRepo;
import com.example.graduationproject.Repository.VoteRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final RestaurantRepo restaurantRepo;
    private final UserRepo userRepo;
    private final VoteRepo voteRepo;

    public UserService(RestaurantRepo restaurantRepo, UserRepo userRepo, VoteRepo voteRepo) {
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
        this.voteRepo = voteRepo;
    }

    @Transactional
    public Vote vote(Integer userId, Integer restaurantId) {
        Vote vote = voteRepo.findByUserIdAndDateVote(userId, LocalDate.now());
        if (vote == null) {
            vote = new Vote();
        }
        if (vote.getDateVote() != null && LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            System.out.println("уже поздно");
            return vote;
        }
        vote.setDateVote(LocalDate.now());
        vote.setUser(userRepo.findById(userId).get());
        vote.setRestaurant(restaurantRepo.findById(restaurantId).get());
        return vote;
    }
}

