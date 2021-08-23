package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.ExceptionHandling.Restaurant.NoSuchRestaurantException;
import com.example.graduationproject.Repository.RestaurantRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;

    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Restaurant getRestaurant(Integer id) {
        return restaurantRepo.findById(id).orElse(null);
    }

    @Cacheable("restaurant")
    public List<Restaurant> getRestaurants() {
//        Select * from RESTAURANT r join VOTE v on r.ID = v.RESTAURANT_ID where v.DATE_INPUT = '2021-08-16'
//        return restaurantRepo.getAllWithMeal(LocalDate.now().minusDays(1));
//        List<Restaurant> list = restaurantRepo.findAllByOrderByName();
//        System.out.println(list.get(0).getVotes().size());
//        System.out.println(11111);

//        List<Meal> meals = restaurantRepo.getServers(LocalDate.now().minusDays(1));
        List<Restaurant> restaurants = restaurantRepo.findAll();
        restaurants
                .forEach(restaurant -> restaurant.setMeals(restaurantRepo.getServers(restaurant.getId(), LocalDate.now())));
        return restaurants;
    }

    @Transactional
    @CacheEvict(cacheNames="restaurant", allEntries=true)
    public Restaurant createAndSaveRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    @Transactional
    @CacheEvict(cacheNames="restaurant", allEntries=true)
    public void deleteRestaurant(Integer id) {
        restaurantRepo.deleteById(id);
    }
}