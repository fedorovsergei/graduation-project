package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.ExceptionHandling.Meal.NoSuchMealException;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantException;
import com.example.graduationproject.Repository.MealRepo;
import com.example.graduationproject.Repository.RestaurantRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MealService {

    private final MealRepo mealRepo;
    private final RestaurantRepo restaurantRepo;

    public MealService(MealRepo mealRepo, RestaurantRepo restaurantRepo) {
        this.mealRepo = mealRepo;
        this.restaurantRepo = restaurantRepo;
    }

    public Meal getMeal(Integer restaurantId, Integer mealId) {
        Meal meal = mealRepo.findById(mealId).orElse(null);
        if (meal != null && meal.getRestaurant() != null && restaurantId.equals(meal.getRestaurant().getId())) {
            return meal;
        }
        return null;
    }

    @Cacheable("meal")
    public List<Meal> getAllMeal(Integer restaurantId) {
        return mealRepo.findAllByRestaurantId(restaurantId);
    }

    @Transactional
    @CacheEvict(cacheNames="restaurant", allEntries=true)
    public Meal createAndSaveMeal(Integer restaurantId, Meal meal) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);
        if (restaurant == null) {
            return null;
        }
        meal.setRestaurant(restaurant);
        return mealRepo.save(meal);
    }


    @Transactional
    @CacheEvict(cacheNames="restaurant", allEntries=true)
    public void deleteMeal(Integer restaurantId, Integer mealId) {
        Meal meal = mealRepo.findById(mealId).orElse(null);
        if (meal != null && meal.getRestaurant() != null && restaurantId.equals(meal.getRestaurant().getId())) {
            mealRepo.deleteById(mealId);
        }
    }
}