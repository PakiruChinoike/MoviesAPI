package io.github.pakiruchinoike.movies.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.pakiruchinoike.movies.domain.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    
    @Query(" select m from Movie m where m.name=:name ")
    Optional<Movie> findByName(@Param("name") String name);

    @Query(" select m from Movie m where m.id=:id or m.name=:name ")
    Optional<Movie> findByIdOrName(@Param("id") Long id, @Param("name") String name);

}
