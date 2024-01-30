package io.github.pakiruchinoike.movies.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.pakiruchinoike.movies.domain.entity.Review;
import io.github.pakiruchinoike.movies.rest.dto.MovieNameDTO;
import io.github.pakiruchinoike.movies.rest.dto.ReviewDTO;
import io.github.pakiruchinoike.movies.service.ReviewService;
import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    
    @Autowired
    private ReviewService service;

    @GetMapping("{id}")
    public Review getReview(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/name")
    public List<Review> getReviewByMovieName(@RequestBody MovieNameDTO dto) {
        String name = dto.getName();
        
        return service.findByMovieName(name);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Review postReview(@RequestBody @Valid ReviewDTO dto) {
        return service.save(dto);
    }

    @PostMapping("{id}")
    @ResponseStatus(CREATED)
    public void updateReview(@RequestBody @Valid ReviewDTO dto, @PathVariable Long id) {
        service.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteReview(@PathVariable Long id) {
        service.delete(id);
    }

}
