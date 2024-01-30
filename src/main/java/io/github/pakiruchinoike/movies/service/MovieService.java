package io.github.pakiruchinoike.movies.service;

import java.util.List;

import io.github.pakiruchinoike.movies.domain.entity.Movie;
import io.github.pakiruchinoike.movies.rest.dto.MovieDTO;

public interface MovieService {

    Movie save(MovieDTO dto);
    Movie findById(Long id);
    Movie findByName(String name);
    List<Movie> findAll();
    List<Movie> findNew();
    void update(MovieDTO dto, Long id);
    void delete(Long id);
    
}
