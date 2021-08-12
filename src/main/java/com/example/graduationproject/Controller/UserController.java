package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Vote;
import com.example.graduationproject.Services.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{userId}")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/vote/{restaurantId}")
    public Vote vote(@PathVariable Integer userId, @PathVariable Integer restaurantId) {
        return userService.vote(userId, restaurantId);
    }
}
