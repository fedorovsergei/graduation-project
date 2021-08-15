package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Repository.RestaurantRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;

    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Restaurant getRestaurant(Integer id) {
        try {
            return restaurantRepo.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Restaurant> getRestaurants() {
        //Select * from RESTAURANT r join VOTE v on r.ID = v.RESTAURANT_ID where v.DATE_INPUT = '2021-08-16'
        return restaurantRepo.findAllByOrderByName();
    }

    @Transactional
    public Restaurant createAndSaveRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    @Transactional
    public void deleteRestaurant(Integer id) {
        restaurantRepo.deleteById(id);
    }
}
