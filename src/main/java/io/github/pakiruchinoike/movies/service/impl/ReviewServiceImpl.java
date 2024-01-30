package io.github.pakiruchinoike.movies.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.pakiruchinoike.movies.domain.entity.Movie;
import io.github.pakiruchinoike.movies.domain.entity.Review;
import io.github.pakiruchinoike.movies.domain.repositories.MovieRepository;
import io.github.pakiruchinoike.movies.domain.repositories.ReviewRepository;
import io.github.pakiruchinoike.movies.exception.ServiceRuleException;
import io.github.pakiruchinoike.movies.rest.dto.ReviewDTO;
import io.github.pakiruchinoike.movies.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    
    private final ReviewRepository repository;
    private final MovieRepository movieRepository;

    @Override
    @Transactional
    public Review save(ReviewDTO dto) {
        Review review = objectFromDto(dto);

        return repository.save(review);
    }
    
    @Override
    public Review findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    public List<Review> findByMovieName(String movieName) {
        return repository.findByMovieName(movieName);
    }

    @Override
    @Transactional
    public void update(ReviewDTO dto, Long id) {
        Review review = objectFromDto(dto);

        repository.findById(id)
            .map(r -> {
                review.setId(id);
                repository.save(review);
                return r;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(r -> {
                repository.delete(r);
                return r;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    private Review objectFromDto(ReviewDTO dto) {
        Movie movie = movieRepository.findByIdOrName(
            dto.getMovieId(), dto.getMovieName())
            .orElseThrow(() -> new ServiceRuleException());

        return Review.builder()
            .review(dto.getReview())
            .points(dto.getPoints())
            .movie(movie)
            .build();
    }

}
