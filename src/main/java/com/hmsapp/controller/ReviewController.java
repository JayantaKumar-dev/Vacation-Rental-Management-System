package com.hmsapp.controller;

import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.payload.ProfileDto;
import com.hmsapp.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public String addReview(@RequestBody Reviews review, @RequestParam long propertyId, @AuthenticationPrincipal User user) {
        String s = reviewService.addReview(review, propertyId, user);
        return s;
    }

    @GetMapping("/user/reviews")
    public List<Reviews> viewMyReviews(@AuthenticationPrincipal User user){
        return reviewService.viewMyReviews(user);
    }


}
