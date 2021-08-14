package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantException;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantParamException;
import com.example.graduationproject.Services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping()
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> list = restaurantService.getRestaurants();
        if (list.isEmpty()) {
            throw new NoSuchRestaurantException("There are no restaurants in the database");
        }
        return new ResponseEntity<>(restaurantService.getRestaurants(), HttpStatus.OK);
    }


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


    @PostMapping()
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
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

    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurant == null) {
            throw new NoSuchRestaurantParamException("Couldn't find restaurant");
        }
        Restaurant updateRestaurant = restaurantService.createAndSaveRestaurant(restaurant);
        if (updateRestaurant == null) {
            throw new NoSuchRestaurantException("Failed to update the restaurant to the database");
        }
        return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
    }

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