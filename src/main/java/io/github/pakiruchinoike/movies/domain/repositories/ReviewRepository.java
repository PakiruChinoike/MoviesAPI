package io.github.pakiruchinoike.movies.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.pakiruchinoike.movies.domain.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

    @Query(" select r from Review r left join fetch r.movie where r.movie.name=:name ")
    List<Review> findByMovieName(@Param("name") String movieName);

}
