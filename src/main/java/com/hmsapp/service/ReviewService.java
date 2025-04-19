package com.hmsapp.service;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private PropertyRepository propertyRepository;
    private ReviewsRepository reviewsRepository;

    public ReviewService(PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    public String addReview(Reviews review, long propertyId, User user) {

        Property property = propertyRepository.findById(propertyId).get();

        Reviews reviewsStatus = reviewsRepository.findByPropertyAndUser(property, user);
        if (reviewsStatus!= null) {
            return "Review for this property already exists";
        }else {
            review.setProperty(property);
            review.setUser(user);
            reviewsRepository.save(review);
            return "Review added";
        }
    }


    public List<Reviews> viewMyReviews(User user) {
        return reviewsRepository.findByUser(user);
    }
}
