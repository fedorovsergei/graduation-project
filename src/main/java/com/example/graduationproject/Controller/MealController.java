package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Services.MealService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @GetMapping(value = "{restaurantID}/meal/{mealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal getMeal(@PathVariable Integer restaurantID, @PathVariable Integer mealId) {
        return mealService.getMeal(restaurantID, mealId);
    }
}
