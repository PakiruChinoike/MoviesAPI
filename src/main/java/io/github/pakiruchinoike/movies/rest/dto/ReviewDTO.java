package io.github.pakiruchinoike.movies.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    
    private String review;

    private Integer points;

    private String movieName;

    private Long movieId;

}
