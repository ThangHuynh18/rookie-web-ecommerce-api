package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.ProductRating;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.ProductRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductRatingServiceImpl implements ProductRatingService{

    @Autowired
    private ProductRatingRepository ratingRepository;

    @Override
    public List<ProductRating> retrieveRatings() {
        List<ProductRating> ratings = ratingRepository.findAll();
        return ratings;
    }

    @Override
    public Optional<ProductRating> getRating(Long ratingId) {
        return ratingRepository.findById(ratingId);
    }

    @Override
    public ProductRating saveRating(ProductRating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Long ratingId) throws ResourceNotFoundException {
        ProductRating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("rating not found for this id: " + ratingId));
        this.ratingRepository.delete(rating);
    }

    @Override
    public ProductRating updateRating(ProductRating rating) {
        return ratingRepository.save(rating);
    }
}
