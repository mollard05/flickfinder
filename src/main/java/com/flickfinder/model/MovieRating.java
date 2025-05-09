package com.flickfinder.model;

/**
 * Represents a movieRating with its id, title, rating, votes and year of release.
 */
public class MovieRating extends Movie{
	private int id;
	private String title;
	private float rating;
	private int votes;
	private int year;

	public MovieRating(int id, String title, float rating, int votes, int year) {
		super(id, title, year);
		this.rating = rating;
		this.votes = votes;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	

}
