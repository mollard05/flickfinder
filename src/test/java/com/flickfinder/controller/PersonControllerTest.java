package com.flickfinder.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.dao.PersonDAO;

import io.javalin.http.Context;

/**
 * TODO: Implement this class
 */
class PersonControllerTest {
	
	private Context ctx;
	private PersonDAO personDAO;
	private PersonController personController;
	
	@BeforeEach
	public void setUp() {
		ctx = mock(Context.class);
		personDAO = mock(PersonDAO.class);
		personController = new PersonController(personDAO);
	}
	
	/**
	 * Test method to check if parameter matches for getAllPeople() method.
	 */
	@Test
	public void testGetAllPeople() {
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getAllPeople();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method to check if 500 is thrown when the database has an error with getAllPeople() method.
	 * @throws SQLException
	 */
	@Test
	void testGetAllDatabaseError() throws SQLException {
		when(personDAO.getAllPeople()).thenThrow(new SQLException());
		personController.getAllPeople(ctx);
		verify(ctx).status(500);
	}
	
	/**
	 * Test method to check if parameter matches for getPersonById() method.
	 */
	@Test
	void testGetPersonById() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getPersonById(ctx);
		try {
			verify(personDAO).getPersonById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method to check if 500 is thrown when database has an error with getPersonById() method.
	 * @throws SQLException
	 */
	@Test
	void testGetPersonByIdDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenThrow(new SQLException());
		personController.getPersonById(ctx);
		verify(ctx).status(500);
	}
	
	/**
	 * Test method to check if 404 error is thrown if person not found.
	 * @throws SQLException
	 */
	@Test
	void testExceptionWhenNoPersonFound() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenReturn(null);
		personController.getPersonById(ctx);
		verify(ctx).status(404);
	}

}