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
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

class PersonDAOTest {
	
	private PersonDAO personDAO;
	private Seeder seeder;
	
	/**
	 * Set up seeder before each test.
	 */
	@BeforeEach
	public void setUp() {
		String url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		seeder.createTables();
		Database.getInstance(seeder.getConnection());
		personDAO = new PersonDAO();
	}
	
	/**
	 * Test for getAllPeople() method by checking if correct size.
	 */
	@Test
	public void testGetAllPeople() {
		List<Person> people;
		try {
			people = personDAO.getAllPeople(5);
			assertEquals(5,people.size());
		} catch (SQLException e) {
			fail("SQL Exception");
			e.printStackTrace();
		}
	}
	
	/**
	 * Test for getPersonById() method by checking if name matches.
	 */
	@Test
	public void testGetPersonById() {
		Person person;
		try {
			person = personDAO.getPersonById(1);
			assertEquals("Tim Robbins", person.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Test for getPersonById() if the id passed into the url is invalid (null).
	 */
	@Test
	public void testGetPersonByIdInvalidId() {
		try {
			Person person = personDAO.getPersonById(1000);
			assertEquals(null, person);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getMoviesStarringPerson method. Person should be specified with an id.
	 */
	@Test
	public void testGetMoviesStarringPerson() {
		List<Movie> movieList = new ArrayList<Movie>();
		try {
			movieList = personDAO.getMoviesStarringPerson(4);
			assertEquals("The Godfather",movieList.get(0).getTitle());
			assertEquals("The Godfather: Part II",movieList.get(1).getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests getMoviesStarringPerson method if invalid. Checks if passed through list is empty to determine if valid. 
	 */
	@Test
	public void testGetMoviesStarringPersonInvalidId() {
		List<Movie> movieList = new ArrayList<Movie>();
		boolean isValid = true;
		try {
			movieList = personDAO.getMoviesStarringPerson(1000);
			if (movieList.isEmpty()) {
				isValid = false;
			}
			assertEquals(false,isValid);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllPeopleLimit() {
		try {
			List<Person> people = personDAO.getAllPeople(3);
			assertEquals(3,people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@AfterEach
	public void tearDown() {
		seeder.closeConnection();
	}
}