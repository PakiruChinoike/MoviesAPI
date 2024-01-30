package io.github.pakiruchinoike.movies.domain.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;   

    @Column(name = "minute_length")
    @Builder.Default
    private Integer minuteLength = 0;

    @Column(name = "hour_length")
    @Builder.Default
    private String hourLength = "0h0";

    @OneToMany(mappedBy = "movie")
    private List<Review> review;

    @Column(name = "points")
    @Builder.Default
    private Float points = 0F;

    @Column(name = "review_number")
    @Builder.Default
    private Integer reviewNumber = 0; 

}
