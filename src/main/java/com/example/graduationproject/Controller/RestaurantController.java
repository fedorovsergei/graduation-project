package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Services.RestaurantService;
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
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Integer id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping()
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.setVoteCount(0);
        return restaurantService.createAndSaveRestaurant(restaurant);
    }

    @PutMapping
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createAndSaveRestaurant(restaurant);
    }

    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteRestaurant(id);
        return "Restaurant with id=" + id + " was deleted";
    }
}