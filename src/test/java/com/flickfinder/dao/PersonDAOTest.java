package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * TODO: Implement this class
 */
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
			people = personDAO.getAllPeople();
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
	
	@AfterEach
	public void tearDown() {
		seeder.closeConnection();
	}
}