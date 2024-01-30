package io.github.pakiruchinoike.movies.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.pakiruchinoike.movies.domain.entity.Movie;
import io.github.pakiruchinoike.movies.domain.entity.Review;
import io.github.pakiruchinoike.movies.domain.repositories.MovieRepository;
import io.github.pakiruchinoike.movies.domain.repositories.ReviewRepository;
import io.github.pakiruchinoike.movies.exception.ServiceRuleException;
import io.github.pakiruchinoike.movies.rest.dto.MovieDTO;
import io.github.pakiruchinoike.movies.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    
    private final MovieRepository repository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Movie save(MovieDTO dto) {
        Movie movie = objectFromDto(dto);

        return repository.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        Movie m = repository.findById(id).orElseThrow(() -> new ServiceRuleException());
        updateScores(m);

        return m;
    }

    @Override
    public Movie findByName(String name) {
        Movie m = repository.findByName(name).orElseThrow(() -> new ServiceRuleException());
        updateScores(m);

        return m;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = repository.findAll();
        movies.forEach(m -> {
            updateScores(m);
        });

        return movies;
    }

    @Override
    @Transactional
    public void update(MovieDTO dto, Long id) {
        Movie movie = objectFromDto(dto);

        repository.findById(id)
            .map(m -> {
                movie.setId(id);
                repository.save(movie);
                return m;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    private void updateScores(Movie movie) {
        List<Review> reviews = movie.getReview();

        Float averageScore = averageScore(reviews);
        movie.setPoints(averageScore);
        movie.setReviewNumber(reviews.size());

        repository.save(movie);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(m -> {
                repository.delete(m);
                return m;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    private Movie objectFromDto(MovieDTO dto) {
        List<Review> reviews = reviewRepository.findByMovieName(
            dto.getName()
        );

        if(!reviews.isEmpty()) {
            return Movie.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .minuteLength(dto.getMinuteLength())
            .hourLength(minuteToHour(dto.getMinuteLength()))
            .review(reviews)
            .reviewNumber(reviews.size())
            .points(averageScore(reviews))
            .build();
        } else {
            return Movie.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .minuteLength(dto.getMinuteLength())
            .hourLength(minuteToHour(dto.getMinuteLength()))
            .build();
        }

    }

    private String minuteToHour(Integer minuteLength) {
        Integer hour = minuteLength/60;
        Integer minute = minuteLength-(hour*60);

        String hourLength = hour + "h" + minute;
        return hourLength;
    }

    private Float averageScore(List<Review> reviews) {
        Float totalScore = 0F;

        for (int i = 0; i<reviews.size(); i++) {
            totalScore = totalScore + reviews.get(i).getPoints();
        }

        Float averageScore = (Float)totalScore/reviews.size();
        return averageScore;
    }

}
