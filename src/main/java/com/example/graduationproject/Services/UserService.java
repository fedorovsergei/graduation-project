package com.example.graduationproject.Services;

import com.example.graduationproject.Entity.Restaurant;
import com.example.graduationproject.Entity.User;
import com.example.graduationproject.Repository.RestaurantRepo;
import com.example.graduationproject.Repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class UserService {

//    private final RestaurantRepo restaurantRepo;
//    private final UserRepo userRepo;
//
//    public UserService(RestaurantRepo restaurantRepo, UserRepo userRepo) {
//        this.restaurantRepo = restaurantRepo;
//        this.userRepo = userRepo;
//    }
//
//    @Transactional
//    public boolean setVote(int userId, Integer restaurantId) {
//        User user = userRepo.getById(userId);
//        if (isVoteTodayAgain(user)) {
//            return false;
//        }
//        Integer oldVoteRestaurantId = user.getRestaurantVote();
//        Restaurant newVoteRestaurant = restaurantRepo.getById(restaurantId);
//        if (oldVoteRestaurantId != null) {
//            try {
//                Restaurant oldVoteRestaurant = restaurantRepo.getById(oldVoteRestaurantId);
//                oldVoteRestaurant.decrementVotes();
//                restaurantRepo.save(oldVoteRestaurant);
//            } catch (EntityNotFoundException ignored) {
//            }
//        }
//        newVoteRestaurant.incrementVotes();
//        user.setRestaurantVote(restaurantId);
//        user.setRestaurantVoteDate(LocalDate.now());
//        restaurantRepo.save(newVoteRestaurant);
//        userRepo.save(user);
//        return true;
//    }
//
//    @Transactional
//    public void deleteVoice() {
//        userRepo.findAll().forEach(user -> user.setRestaurantVote(null));
//    }
//
//    public User getUserByName(String userName) {
//        return userRepo.findByName(userName);
//    }
//
//    private boolean isVoteTodayAgain(User user) {
//        if (user.getRestaurantVoteDate() == null) {
//            user.setRestaurantVoteDate(LocalDate.MIN);
//        }
//        return user.getRestaurantVoteDate().equals(LocalDate.now())
//                && LocalTime.now().isAfter(LocalTime.parse("23:00:00"));
//    }
}
