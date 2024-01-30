package io.github.pakiruchinoike.movies.service;

import java.util.List;

import io.github.pakiruchinoike.movies.domain.entity.Review;
import io.github.pakiruchinoike.movies.rest.dto.ReviewDTO;

public interface ReviewService {
    
    Review save(ReviewDTO dto);
    Review findById(Long id);
    List<Review> findByMovieName(String movieName);
    void update(ReviewDTO dto, Long id);
    void delete(Long id);

}
