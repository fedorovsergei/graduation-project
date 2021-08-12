package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Repository.MealRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MealService {

    private final MealRepo mealRepo;

    public MealService(MealRepo mealRepo) {
        this.mealRepo = mealRepo;
    }


    public Meal getMeal(Integer restaurantId, Integer mealId) {
        Meal meal = mealRepo.findById(mealId).get();
        System.out.println(meal);
        if (meal.getRestaurant().getId().equals(restaurantId)) {
            return meal;
        }
        else return null;
    }

//    private final MealRepo mealRepo;
//    private final RestaurantRepo restaurantRepo;
//
//    public MealService(MealRepo mealRepo, RestaurantRepo restaurantRepo) {
//        this.mealRepo = mealRepo;
//        this.restaurantRepo = restaurantRepo;
//    }
//
//    @Transactional
//    public Meal addMeal(Integer id, Meal meal) {
//        if (!isNameValid(meal) || !isPriceValid(meal)) {
//            return null;
//        }
//        Restaurant restaurant = restaurantRepo.getById(id);
//        meal.setRestaurant(restaurant);
//        meal.setDateInput(LocalDate.now());
//        return mealRepo.save(meal);
//    }
//
//    @Transactional
//    public void deleteAll() {
//        mealRepo.deleteAll();
//    }
//
//    @Transactional
//    public void deleteMealForId(Integer id) {
//        mealRepo.deleteById(id);
//    }
//
//    private boolean isNameValid(BaseAbstractEntityName entity) {
//        return (entity.getName() != null || !entity.getName().isEmpty());
//    }
//
//    private boolean isPriceValid(Meal meal) {
//        return (meal.getPrice() > 0);
//    }
}
