package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Repository.MealRepo;
import com.example.graduationproject.Repository.RestaurantRepo;
import com.example.graduationproject.Repository.VoteRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;
    private final MealRepo mealRepo;
    private final VoteRepo voteRepo;

    public RestaurantService(RestaurantRepo restaurantRepo, MealRepo mealRepo, VoteRepo voteRepo) {
        this.restaurantRepo = restaurantRepo;
        this.mealRepo = mealRepo;
        this.voteRepo = voteRepo;
    }

    public Restaurant getRestaurant(Integer id) {
        return restaurantRepo.findById(id).orElse(null);
    }

    @Cacheable("restaurant")
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAllByOrderByName();
        restaurants
                .forEach(restaurant -> {
                            restaurant.setVotes(voteRepo
                                    .findAllByRestaurantIdAndAndDateVote(restaurant.getId(), LocalDate.now()));
                            restaurant.setMeals(mealRepo
                                    .findAllByRestaurantIdAndDateInput(restaurant.getId(), LocalDate.now()));
                        }
                );
        return restaurants;
    }

    @Transactional
    @CacheEvict(cacheNames = "restaurant", allEntries = true)
    public Restaurant createAndSaveRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    @Transactional
    @CacheEvict(cacheNames = "restaurant", allEntries = true)
    public void deleteRestaurant(Integer id) {
        restaurantRepo.deleteById(id);
    }
}