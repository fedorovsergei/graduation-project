package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Entity.User;
import com.example.graduationproject.Entity.Vote;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantException;
import com.example.graduationproject.Repository.RestaurantRepo;
import com.example.graduationproject.Repository.UserRepo;
import com.example.graduationproject.Repository.VoteRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private final RestaurantRepo restaurantRepo;
    private final UserRepo userRepo;
    private final VoteRepo voteRepo;

    public VoteService(RestaurantRepo restaurantRepo, UserRepo userRepo, VoteRepo voteRepo) {
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
        this.voteRepo = voteRepo;
    }

    @Transactional
    @CacheEvict(cacheNames="restaurant", allEntries=true)
    public Vote vote(String userName, Integer restaurantId) {
        User user = null;
        try {
            user = userRepo.findByName(userName);
        } catch (Exception e) {
            return null;
        }
        if (user == null) {
            return null;
        }
        Vote vote = voteRepo.findByUserIdAndDateVote(user.getId(), LocalDate.now());
        if (vote == null) {
            vote = new Vote();
        }
        if (vote.getDateVote() != null && LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            System.out.println("уже поздно");
            return null;
        }
        vote.setDateVote(LocalDate.now());
        vote.setUser(user);
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);
        if (restaurant == null) {
            return null;
        }
        vote.setRestaurant(restaurant);
        return voteRepo.save(vote);
    }
}