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
	
	public List<Person> getAllPeople() throws SQLException {
		List<Person> people = new ArrayList<>();

		Statement statement = connection.createStatement();
		
		// I've set the limit to 10 for development purposes - you should do the same.
		ResultSet rs = statement.executeQuery("select * from people LIMIT 50");
		
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
		
		// return null if the id does not return a movie.

		return null;

	}
}
