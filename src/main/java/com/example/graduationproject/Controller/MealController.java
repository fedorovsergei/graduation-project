package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.ExceptionHandling.Meal.NoSuchMealException;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantParamException;
import com.example.graduationproject.Services.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/restaurant/{restaurantId}/meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @GetMapping()
    public ResponseEntity<List<Meal>> getAllMeal(@PathVariable Integer restaurantId) {
        if (restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find restaurantId");
        }
        List<Meal> list = mealService.getAllMeal(restaurantId);
        if (list.isEmpty()) {
            throw new NoSuchMealException("There are no meals in the database");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/{mealId}")
    public ResponseEntity<Meal> getMeal(@PathVariable Integer restaurantId, @PathVariable Integer mealId) {
        if (mealId == null || restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find mealId or restaurantId");
        }
        Meal meal = mealService.getMeal(restaurantId, mealId);
        if (meal == null) {
            throw new NoSuchMealException("There is no meal with Id=" + mealId + " in Database");
        }
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }


    @GetMapping("/today")
    public ResponseEntity<List<Meal>> getTodayAllMeal(@PathVariable Integer restaurantId) {
        if (restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find restaurantId");
        }
        List<Meal> list = mealService.getTodayAllMeal(restaurantId);
        if (list.isEmpty()) {
            throw new NoSuchMealException("There are no meals in the database");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Meal> addMeal(@PathVariable Integer restaurantId, @RequestBody Meal meal) {
        if (meal == null || restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find meal or restaurantId");
        }
        meal.setDateInput(LocalDate.now());
        Meal saveMeal = mealService.createAndSaveMeal(restaurantId, meal);
        if (saveMeal == null) {
            throw new NoSuchMealException("Failed to save the meal to the database");
        }
        return new ResponseEntity<>(saveMeal, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer restaurantId, @RequestBody Meal meal) {
        if (meal == null || restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find meal or restaurantId");
        }
        meal.setDateInput(LocalDate.now());
        Meal updateMeal = mealService.createAndSaveMeal(restaurantId, meal);
        if (updateMeal == null) {
            throw new NoSuchMealException("Failed to update the meal to the database");
        }
        return new ResponseEntity<>(updateMeal, HttpStatus.OK);
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<String> deleteMeal(@PathVariable Integer restaurantId, @PathVariable Integer mealId) {
        if (mealId == null || restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find mealId or restaurantId");
        }
        Meal meal = mealService.getMeal(restaurantId, mealId);
        if (meal == null) {
            throw new NoSuchMealException("There is no meal with Id=" + mealId + " in Database");
        }
        mealService.deleteMeal(restaurantId, mealId);
        return new ResponseEntity<>("Meal with id=" + mealId + " was deleted", HttpStatus.OK);
    }
}
