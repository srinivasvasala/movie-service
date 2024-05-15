package com.srinivas.movieservice.Services;

import com.srinivas.movieservice.DTO.MovieDTO;
import com.srinivas.movieservice.Entities.Movie;
import com.srinivas.movieservice.Exceptions.MovieExistEx;
import com.srinivas.movieservice.Exceptions.MovieNotFound;
import com.srinivas.movieservice.Repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieServices {
    private final MovieRepository movieRepository;
    public MovieServices(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        Optional<Movie> existingMovie = movieRepository.findByName(movieDTO.getName());
        if (existingMovie.isPresent()) {
            throw new MovieExistEx("Movie already exists with name: "+movieDTO.getName());
        }
        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setActors(movieDTO.getActors());
       return movieRepository.save(movie);
    }
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Movie updateMovie(Long id,MovieDTO movieDTO) {
        Optional<Movie> existingMovie = movieRepository.findById(id);
        if (existingMovie.isEmpty()) {
            throw new MovieNotFound("Movie not found with id: " + id);
        }
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFound("Movie not found with id: " + id));
        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setActors(movieDTO.getActors());
        return movieRepository.save(movie);
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }




}
