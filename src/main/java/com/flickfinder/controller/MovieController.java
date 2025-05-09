package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class MovieController {

	/**
	 * The movie data access object.
	 */
	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initializes the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllMovies(Context ctx) {
		int limit = ctx.queryParam("limit") != null 
				? Integer.parseInt(ctx.queryParam("limit"))
				: 50;
		// used web-site https://tech-docs.corndel.com/javalin/query-params for information
		try {
			if (limit < 1) {
				throw new IllegalArgumentException();
			} else {
				ctx.json(movieDAO.getAllMovies(limit));
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
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the people with the specified movie id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPeopleByMovieId(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Person> people = movieDAO.getPeopleByMovieId(id);
			if (people.isEmpty()) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getPeopleByMovieId(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the Ratings of movies based on a specified year.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getRatingsByYear(Context ctx) {
		int year = Integer.parseInt(ctx.pathParam("year"));
		int limit = ctx.queryParam("limit") != null 
				? Integer.parseInt(ctx.queryParam("limit"))
				: 50;
		int votes = ctx.queryParam("votes") != null
				? Integer.parseInt(ctx.queryParam("votes"))
				: 1000;
		try {
			if (limit < 1 || votes < 1) {
				throw new IllegalArgumentException();
			} else {
				List<MovieRating> movies = movieDAO.getRatingsByYear(year, limit, votes);
				if (movies.isEmpty()) {
					ctx.status(404);
					ctx.result("Year not found");
					return;
				}
				ctx.json(movieDAO.getRatingsByYear(year,limit,votes));
			}
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			ctx.status(400);
			ctx.result("Invalid Argument Error");
			e.printStackTrace();
		}
	}

}