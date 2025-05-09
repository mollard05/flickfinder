package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * The Data Access Object for the Movie table.
 * 
 * This class is responsible for getting data from the Movies table in the
 * database.
 * 
 */
public class MovieDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 * 
	 */
	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @return a list of all movies in the database
	 * @throws SQLException if a database error occurs
	 */

	public List<Movie> getAllMovies(int limit) throws SQLException {
		List<Movie> movies = new ArrayList<>();
		
		String statement = "select * from movies LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return movies;
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param id the id of the movie
	 * @return the movie with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Movie getMovieById(int id) throws SQLException {

		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}
		
		// return null if the id does not return a movie.

		return null;

	}
	
	/**
	 * Returns list of People from specified movie id.
	 * 
	 * @param movieId id of the movie
	 * @return list of people based on movie id
	 * @throws SQLException if database error occurs
	 */
	public List<Person> getPeopleByMovieId(int movieId) throws SQLException {
		List<Person> stars = new ArrayList<Person>();
		
		String statement = "select * from people inner join stars on people.id = stars.person_id where stars.movie_id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1,movieId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Person newPerson = new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
			stars.add(newPerson);
		}
		return stars;
	}
	
	/**
	 * Returns list of movie rating objects from specified year.
	 * 
	 * @param year year of release of the movie
	 * @param limit limit of how many to show
	 * @param votes how many votes movies listed should be higher than
	 * @return list of movie ratings based on year
	 * @throws SQLException if database error occurs
	 */
	public List<MovieRating> getRatingsByYear(int year, int limit, int votes) throws SQLException {
		List<MovieRating> movies = new ArrayList<>();
		
		String statement = "select movies.id, movies.title, ratings.rating, ratings.votes, movies.year from movies inner join ratings on movies.id = ratings.movie_id where movies.year = ? and ratings.votes > ? order by ratings.rating desc LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, year);
		ps.setInt(2,votes);
		ps.setInt(3,limit);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MovieRating newMovieRating = new MovieRating(rs.getInt("id"), rs.getString("title"), rs.getFloat("rating"), rs.getInt("votes"), rs.getInt("year"));
			movies.add(newMovieRating);
		}
		return movies;
	}

}
