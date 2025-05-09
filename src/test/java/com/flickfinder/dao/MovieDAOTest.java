package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * Test for the Movie Data Access Object.
 */

class MovieDAOTest {

	/**
	 * The movie data access object.
	 */

	private MovieDAO movieDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables.
	 * We are using an in-memory database for testing purposes.
	 * This gets passed to the Database class to get a connection to the database.
	 * As it's a singleton class, the entire application will use the same
	 * connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		movieDAO = new MovieDAO();

	}

	/**
	 * Tests the getAllMovies method.
	 * We expect to get a list of all movies in the database.
	 * We have seeded the database with 5 movies, so we expect to get 5 movies back.
	 * At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllMovies() {
		try {
			List<Movie> movies = movieDAO.getAllMovies(5);
			//limit set to 5 as that is all there is in the seeder
			assertEquals(5, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method.
	 * We expect to get the movie with the specified id.
	 */
	@Test
	void testGetMovieById() {
		Movie movie;
		try {
			movie = movieDAO.getMovieById(1);
			assertEquals("The Shawshank Redemption", movie.getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetMovieByIdInvalidId() {
		try {
			Movie movie = movieDAO.getMovieById(1000);
			assertEquals(null, movie);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests getPeopleByMovieId method. Movie should be specified with an id.
	 */
	@Test
	public void testGetPeopleByMovieId() {
		List<Person> peopleList = new ArrayList<Person>();
		try {
			peopleList = movieDAO.getPeopleByMovieId(1);
			assertEquals("Tim Robbins",peopleList.get(0).getName());
			assertEquals("Morgan Freeman",peopleList.get(1).getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getPeopleByMovieId method if invalid. Checks if passed through list is empty to determine if valid. 
	 */
	@Test
	public void testGetPeopleByInvalidMovieId() {
		List<Person> peopleList = new ArrayList<Person>();
		boolean isValid = true;
		try {
			peopleList = movieDAO.getPeopleByMovieId(1000);
			if (peopleList.isEmpty()) {
				isValid = false;
			}
			assertEquals(false,isValid);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests testGetAllMovies method if limit is working correctly.
	 * Checks if list size matches size in parameter.
	 */
	@Test
	public void testGetAllMoviesLimit() {
		try {
			List<Movie> movies = movieDAO.getAllMovies(3);
			assertEquals(3,movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getRatingByYear method.
	 * Checks if movies returned in correct order based on year "1994".
	 */
	@Test
	public void testGetRatingsByYear() {
		List<MovieRating> ratingList = new ArrayList<MovieRating>();
		try {
			ratingList = movieDAO.getRatingsByYear(1994, 10, 50);
			assertEquals("The Shawshank Redemption",ratingList.get(0).getTitle());
			assertEquals("Forrest Gump", ratingList.get(1).getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getRatinsgByYear method if invalid.
	 * Checks if list is empty if an invalid year is passed as a parameter.
	 */
	@Test
	public void testGetRatingsByYearInvalid() {
		List<MovieRating> ratingList = new ArrayList<MovieRating>();
		boolean isValid = true;
		try {
			ratingList = movieDAO.getRatingsByYear(2024, 10, 50);
			if (ratingList.isEmpty()) {
				isValid = false;
			}
			assertEquals(false,isValid);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getRatingsByYear method if limit is correct.
	 * Checks if the size of the list matches that passed in parameter.
	 */
	@Test
	public void testGetRatingsByYearLimit() {
		try {
			List<MovieRating> movieRating = movieDAO.getRatingsByYear(1994, 1, 50);
			assertEquals(1,movieRating.size());
			assertEquals("The Shawshank Redemption",movieRating.get(0).getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getRatingsByYear method if votes are correct.
	 * Checks if movie returned matches the result when over 2300000 votes.
	 */
	@Test
	public void testGetRatingsByYearVotes() {
		try {
			List<MovieRating> movieRating = movieDAO.getRatingsByYear(1994, 1, 2300000);
			assertEquals("Forrest Gump", movieRating.get(0).getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}

}