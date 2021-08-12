package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Services.MealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurant/{restaurantId}/meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @GetMapping("/{mealId}")
    public Meal getMeal(@PathVariable Integer restaurantId, @PathVariable Integer mealId) {
        return mealService.getMeal(restaurantId, mealId);
    }

    @GetMapping()
    public List<Meal> getAllMeal(@PathVariable Integer restaurantId) {
        return mealService.getAllMeal(restaurantId);
    }

    @GetMapping("/today")
    public List<Meal> getTodayAllMeal(@PathVariable Integer restaurantId) {
        return mealService.getTodayAllMeal(restaurantId);
    }


    @PostMapping()
    public Meal addMeal(@PathVariable Integer restaurantId, @RequestBody Meal meal) {
        return mealService.createAndSaveMeal(restaurantId, meal);
    }

    @PutMapping
    public Meal updateMeal(@PathVariable Integer restaurantId, @RequestBody Meal meal) {
        return mealService.createAndSaveMeal(restaurantId, meal);
    }

    @DeleteMapping("/{mealId}")
    public String deleteMeal(@PathVariable Integer restaurantId, @PathVariable Integer mealId) {
        mealService.deleteMeal(restaurantId, mealId);
        return "Restaurant with id=" + mealId + " was deleted";
    }
}
