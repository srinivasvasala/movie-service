package com.srinivas.movieservice.Controller;

import com.srinivas.movieservice.DTO.MovieDTO;
import com.srinivas.movieservice.Entities.Movie;
import com.srinivas.movieservice.Services.MovieServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/v1")
public class MovieController {
    private final MovieServices  movieServices;
    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
    }

    @PostMapping("/addMovies")
    public ResponseEntity<Movie> addMovie( @RequestBody MovieDTO movieDTO) {
        Movie addedMovie = movieServices.addMovie(movieDTO);
        return ResponseEntity.ok(addedMovie);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieServices.getAllMovies());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(name = "id") Long id, @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieServices.updateMovie(id, movieDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable(name = "id") Long id) {
        movieServices.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
