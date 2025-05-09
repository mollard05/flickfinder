package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class PersonController {

	private final PersonDAO personDAO;
	
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	/**
	 * Returns a list of all people in database.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllPeople(Context ctx) {
		int limit = ctx.queryParam("limit") != null
				? Integer.parseInt(ctx.queryParam("limit"))
				: 50;
		// used web-site https://tech-docs.corndel.com/javalin/query-params for information
		try {
			if (limit < 1) {
				throw new IllegalArgumentException();
			} else {
				ctx.json(personDAO.getAllPeople(limit));
			}
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			ctx.status(400);
			ctx.result("Invalid Argument error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Return person with specific Id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPersonById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Return list of movies starring a specified person id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMoviesStarringPerson(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(id);
			if (movies.isEmpty()) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getMoviesStarringPerson(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
}