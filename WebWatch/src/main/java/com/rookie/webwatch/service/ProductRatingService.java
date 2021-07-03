package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.ProductRating;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductRatingService {
    public List<ProductRating> retrieveRatings();

    public Optional<ProductRating> getRating(Long ratingId);

    public ProductRating saveRating(ProductRating rating);

    public void deleteRating(Long ratingId) throws ResourceNotFoundException;

    public ProductRating updateRating(ProductRating rating);
}
