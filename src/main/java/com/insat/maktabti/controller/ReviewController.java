package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.ReviewDao;
import com.insat.maktabti.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @Autowired
    private ReviewDao reviewDao;

    @GetMapping(value = "/Reviews")
    public Iterable<Review> getAllReviews() {
        Iterable<Review> reviews = reviewDao.findAll();
        return reviews;
    }

    @GetMapping(value = "/Review/{id}")
    public Review getOneReview(@PathVariable int id) {
        return reviewDao.findById(id);
    }

    @DeleteMapping(value = "/Review/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewDao.deleteById(id);
    }

    @PutMapping(value = "/Review")
    public void updateProduit(@RequestBody Review review) {
        reviewDao.save(review);
    }

    @PostMapping(value = "/Review")
    public Review createReview(@RequestBody Review review) {
        Review reviewAdded = reviewDao.save(review);
        return reviewAdded;
    }

}
