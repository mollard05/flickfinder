package com.flickfinder.controller;

import java.sql.SQLException;

import com.flickfinder.dao.PersonDAO;

import io.javalin.http.Context;

public class PersonController {

	// to complete the must-have requirements you need to add the following methods:
	// getAllPeople
	// getPersonById
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.  
	private final PersonDAO personDAO;
	
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	public void getAllPeople(Context ctx) {
		try {
			ctx.json(personDAO.getAllPeople());
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
}