package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

public class PersonDAO {

	private final Connection connection;
	
	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}
	
	/**
	 * Returns a list of all people in the database.
	 * 
	 * @return a list of all people in the database
	 * @throws SQLException if a database error occurs
	 */
	public List<Person> getAllPeople(int limit) throws SQLException {
		List<Person> people = new ArrayList<>();
		
		String statement = "select * from people LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}
	
	/**
	 * Returns the person with the specified id.
	 * 
	 * @param id the id of the person
	 * @return the person with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Person getPersonById(int id) throws SQLException {

		String statement = "select * from people where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
		}

		return null;

	}
	
	/**
	 * Returns list of movies from specified person id.
	 * 
	 * @param personId id of the person
	 * @return list of movies based on person id
	 * @throws SQLException if database error occurs
	 */
	public List<Movie> getMoviesStarringPerson(int personId) throws SQLException {
		List<Movie> movies = new ArrayList<Movie>();
		
		String statement = "select * from movies inner join stars on movies.id = stars.movie_id where stars.person_id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1,personId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Movie newMovie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
			movies.add(newMovie);
		}
		return movies;
	}
}
