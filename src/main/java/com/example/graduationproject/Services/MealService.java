package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Repository.MealRepo;
import com.example.graduationproject.Repository.RestaurantRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        Meal meal = mealRepo.findById(mealId).get();
        if (meal.getRestaurant().getId().equals(restaurantId)) {
            return meal;
        } else return null;
    }

    public List<Meal> getAllMeal(Integer restaurantId) {
        return mealRepo.findAllByRestaurantId(restaurantId);
    }

    public List<Meal> getTodayAllMeal(Integer restaurantId) {
        return mealRepo.findAllByRestaurantIdAndDateInput(restaurantId, LocalDate.now());
    }


    @Transactional
    public Meal createAndSaveMeal(Integer restaurantId, Meal meal) {
        meal.setRestaurant(restaurantRepo.findById(restaurantId).get());
        return mealRepo.save(meal);
    }


    @Transactional
    public void deleteMeal(Integer restaurantId, Integer mealId) {
        Meal meal = mealRepo.findById(mealId).get();
        if (meal.getRestaurant().getId().equals(restaurantId)) {
            mealRepo.deleteById(mealId);
        }
    }
}
