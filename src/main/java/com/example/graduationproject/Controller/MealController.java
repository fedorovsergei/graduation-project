package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.ExceptionHandling.Meal.NoSuchMealException;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantParamException;
import com.example.graduationproject.Services.MealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/restaurant/{restaurantId}/meal")
@Validated
@Tag(name = "Meal controller", description = "only Admins have access")
public class MealController {
    private final MealService mealService;


    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @Operation(
            summary = "Getting all meals",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @GetMapping()
    public ResponseEntity<List<Meal>> getAllMeal(@PathVariable Integer restaurantId, Principal principal) {
        if (restaurantId == null) {
            throw new NoSuchRestaurantParamException("Couldn't find restaurantId");
        }
        List<Meal> list = mealService.getAllMeal(restaurantId);
        if (list.isEmpty()) {
            throw new NoSuchMealException("There are no meals in the database");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Operation(
            summary = "Getting a meal by id",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
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


    @Operation(
            summary = "Adding a meal by id",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @PostMapping()
    public ResponseEntity<Meal> addMeal(@PathVariable Integer restaurantId, @Valid @RequestBody Meal meal) {
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


    @Operation(
            summary = "Update a meal by id",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @PutMapping
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer restaurantId, @Valid @RequestBody Meal meal) {
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


    @Operation(
            summary = "Delete a meal by id",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
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