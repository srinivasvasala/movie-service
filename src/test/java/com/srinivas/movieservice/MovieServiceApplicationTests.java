package com.srinivas.movieservice;

import com.srinivas.movieservice.DTO.MovieDTO;
import com.srinivas.movieservice.Entities.Movie;
import com.srinivas.movieservice.Repository.MovieRepository;
import com.srinivas.movieservice.Services.MovieServices;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MovieServiceApplicationTests {


	@InjectMocks
	private MovieServices movieServices;
    @Mock
    private static MovieRepository movieRepository;

	@Test
	public void testAddMovie(){
		MovieDTO movieDto = new MovieDTO();
		movieDto.setName("testMovie");
		movieDto.setGenre("testAction");
		movieDto.setLanguage("Telugu");
		List<String> actors = Arrays.asList("srinivas","vasala");
		movieDto.setActors(actors);

		//mock the behaviour of movieRepository
		when(movieRepository.findByName(movieDto.getName())).thenReturn(Optional.empty());
		when(movieRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		//Act
		Movie result = movieServices.addMovie(movieDto);
		//Assert
		assertEquals("testMovie", movieDto.getName());
		assertEquals( "Telugu", movieDto.getLanguage());
		assertEquals("testAction", movieDto.getGenre());
		assertEquals(movieDto.getActors(), movieDto.getActors());

		//verify
		Mockito.verify(movieRepository,times(1)).findByName(movieDto.getName());
		Mockito.verify(movieRepository, times(1)).save(any());

	}

}
