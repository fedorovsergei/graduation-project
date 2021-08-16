package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Repository.MealRepo;
import com.example.graduationproject.Repository.RestaurantRepo;
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
        try {
            Meal meal = mealRepo.findById(mealId).get();
            if (meal != null && meal.getRestaurant() != null && restaurantId.equals(meal.getRestaurant().getId())) {
                return meal;
            } else return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Meal> getAllMeal(Integer restaurantId) {
        return mealRepo.findAllByRestaurantId(restaurantId);
    }


    @Transactional
    public Meal createAndSaveMeal(Integer restaurantId, Meal meal) {
        try {
            meal.setRestaurant(restaurantRepo.findById(restaurantId).get());
            return mealRepo.save(meal);
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public void deleteMeal(Integer restaurantId, Integer mealId) {
        Meal meal = mealRepo.findById(mealId).get();
        if (meal != null && meal.getRestaurant() != null && restaurantId.equals(meal.getRestaurant().getId())) {
            mealRepo.deleteById(mealId);
        }
    }
}