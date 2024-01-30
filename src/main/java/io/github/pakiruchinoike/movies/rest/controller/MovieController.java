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

import io.github.pakiruchinoike.movies.domain.entity.Movie;
import io.github.pakiruchinoike.movies.rest.dto.MovieDTO;
import io.github.pakiruchinoike.movies.rest.dto.MovieNameDTO;
import io.github.pakiruchinoike.movies.service.MovieService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    
    @Autowired
    private MovieService service;

    @GetMapping("{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/name")
    public Movie getMovieByName(@RequestBody MovieNameDTO dto) {
        String name = dto.getName();

        return service.findByName(name);
    }

    @GetMapping("/new")
    public List<Movie> getNewMovies() {
        return service.findNew();
    }

    @GetMapping
    public List<Movie> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Movie postMovie(@RequestBody MovieDTO dto) {
        return service.save(dto);
    }

    @PostMapping("{id}")
    @ResponseStatus(CREATED)
    public void updateMovie(@RequestBody MovieDTO dto, @PathVariable Long id) {
        service.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        service.delete(id);
    }

}
