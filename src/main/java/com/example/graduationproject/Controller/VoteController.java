package com.example.graduationproject.Controller;

import com.example.graduationproject.Entity.Vote;
import com.example.graduationproject.ExceptionHandling.Vote.NoSuchVoteException;
import com.example.graduationproject.ExceptionHandling.Vote.NoSuchVoteParamException;
import com.example.graduationproject.Services.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
@Tag(name = "Voting controller", description = "Only users have access")

public class VoteController {

    private final VoteService userService;

    public VoteController(VoteService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Voting for a restaurant",
            description = "The user can vote at any time, but can only re-vote until 11:00"
    )
    @PostMapping("/{restaurantId}")
    public ResponseEntity<Vote> vote(@PathVariable Integer restaurantId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        if (userName == null || restaurantId == null) {
            throw new NoSuchVoteParamException("Couldn't find userId or restaurantId");
        }
        Vote vote = userService.vote(userName, restaurantId);
        if (vote == null) {
            throw new NoSuchVoteException("An error occurred while voting");
        }
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }
}