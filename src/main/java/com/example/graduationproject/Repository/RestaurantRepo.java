package com.example.graduationproject.Repository;

import com.example.graduationproject.Entity.Meal;
import com.example.graduationproject.Entity.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {

//    @Query("Select r from Restaurant r join Meal m on r.id = m.restaurant.id where m.dateInput = '2021-08-18'")
//    @Query("Select r from Restaurant r inner join fetch r.meals as customer " +
//            "left join fetch r.meals as Meal where customer.id= :customerId and o.orderStatus='DELIVERED'")
//    @Query("select r from Restaurant r join Meal m on r.id = m.restaurant.id where m.dateInput=?1 group by r.id")
//    List<Restaurant> getAllWithMeal(LocalDate date);

//    @Query("select r from Restaurant r join where Meal m.dateInput =?1")
//    List<Restaurant> getNewsByLikeOrDizlike_Token(LocalDate date);

//    List<Restaurant> findRestaurantsByMealsIsContain

//    @Query("select r from Restaurant r fetch all r")
//    List<Restaurant> findAllByFeedbacks_Token(LocalDate date);

    @Query("Select m from Meal m where m.restaurant.id = :restaurantId and m.dateInput = :date")
//    @Query("Select m from Meal m where m.dateInput = :date")
    List<Meal> getServers(Integer restaurantId, LocalDate date);

}