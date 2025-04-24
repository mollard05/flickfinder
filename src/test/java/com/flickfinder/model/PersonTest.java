package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * TODO: Implement this class
 * 
 */

class PersonTest {

	private Person person;
	
	/**
	 * Set up a person
	 */
	@BeforeEach
	public void setUp() {
		person = new Person(1,"Tim Robbins",1958);
	}
	
	/**
	 * Test method for getter methods
	 */
	@Test
	public void testGetter() {
		assertEquals(1,person.getId());
		assertEquals("Tim Robbins",person.getName());
		assertEquals(1958,person.getBirth());
	}
	/**
	 * Test method for setter methods
	 */
	@Test
	public void testSetter() {
		person.setId(2);
		person.setName("Molly Sheppard");
		person.setBirth(2005);
		assertEquals(2,person.getId());
		assertEquals("Molly Sheppard",person.getName());
		assertEquals(2005,person.getBirth());
	}
}