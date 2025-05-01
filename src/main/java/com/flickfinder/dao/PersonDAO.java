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

/**
 * TODO: Implement this class
 * 
 */
public class PersonDAO {

	// for the must have requirements, you will need to implement the following
	// methods:
	// - getAllPeople()
	// - getPersonById(int id)
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.  
	
	private final Connection connection;
	
	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}
	
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
