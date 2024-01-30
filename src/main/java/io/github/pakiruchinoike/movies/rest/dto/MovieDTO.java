package io.github.pakiruchinoike.movies.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {
    
    private String name;
    
    private String description;

    @Builder.Default 
    private Integer minuteLength = 0;

}
