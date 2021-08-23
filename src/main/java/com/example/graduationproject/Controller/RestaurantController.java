package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantException;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantParamException;
import com.example.graduationproject.Services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@Tag(name = "Restaurant controller", description = "Only admins have access to methods, " +
        "method \"allForUser\" available to all users")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @Operation(
            summary = "Getting all restaurants",
            description = "Available to all authorized users. The repository result is cached"
    )
    @GetMapping("/allForUser")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> list = restaurantService.getRestaurants();
        if (list.isEmpty()) {
            throw new NoSuchRestaurantException("There are no restaurants in the database");
        }
        list.forEach(restaurant -> restaurant.setVoteCount(restaurant.getVotes().size()));
        return new ResponseEntity<>(restaurantService.getRestaurants(), HttpStatus.OK);
    }


    @Operation(
            summary = "Getting a restaurant by id",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        if (id == null) {
            throw new NoSuchRestaurantParamException("Couldn't find id");
        }
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            throw new NoSuchRestaurantException("There is no restaurant with Id=" + id + " in Database");
        }
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    @Operation(
            summary = "Adding a restaurant by request body",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @PostMapping()
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        if (restaurant == null) {
            throw new NoSuchRestaurantParamException("Couldn't find restaurant");
        }
        restaurant.setVoteCount(0);
        Restaurant saveRestaurant = restaurantService.createAndSaveRestaurant(restaurant);
        if (saveRestaurant == null) {
            throw new NoSuchRestaurantException("Failed to save the restaurant to the database");
        }
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    @Operation(
            summary = "Update a restaurant by request body",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurant(@Valid @RequestBody Restaurant restaurant) {
        if (restaurant == null) {
            throw new NoSuchRestaurantParamException("Couldn't find restaurant");
        }
        Restaurant updateRestaurant = restaurantService.createAndSaveRestaurant(restaurant);
        if (updateRestaurant == null) {
            throw new NoSuchRestaurantException("Failed to update the restaurant to the database");
        }
        return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
    }


    @Operation(
            summary = "Delete a restaurant by id",
            description = "Available only to administrators, in case of errors, a json response is displayed with an indication of the reason"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
        if (id == null) {
            throw new NoSuchRestaurantParamException("Couldn't find id");
        }
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            throw new NoSuchRestaurantException("There is no restaurant with Id=" + id + " in Database");
        }
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>("Restaurant with id=" + id + " was deleted", HttpStatus.OK);
    }
}