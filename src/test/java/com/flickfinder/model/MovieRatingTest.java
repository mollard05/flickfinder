package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for MovieRating Model.
 */
public class MovieRatingTest {
	
	private MovieRating movieRating;
	
	@BeforeEach
	public void setUp() {
		movieRating = new MovieRating(1, "Alien", 8.5f, 1000000, 1979);
	}
	
	@Test
	public void testMovieRatingCreated() {
		assertEquals(1, movieRating.getId());
		assertEquals("Alien",movieRating.getTitle());
		assertEquals(8.5f, movieRating.getRating());
		assertEquals(1000000, movieRating.getVotes());
		assertEquals(1979, movieRating.getYear());
	}
	
	@Test
	public void testMovieRatingSetters() {
		movieRating.setId(2);
		movieRating.setTitle("Aliens");
		movieRating.setRating(8.4f);
		movieRating.setVotes(808000);
		movieRating.setYear(1986);
		assertEquals(2, movieRating.getId());
		assertEquals("Aliens",movieRating.getTitle());
		assertEquals(8.4f, movieRating.getRating());
		assertEquals(808000, movieRating.getVotes());
		assertEquals(1986, movieRating.getYear());
	}
}
