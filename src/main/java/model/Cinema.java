package model;

import java.util.ArrayList;

public class Cinema {
	
	String name;
	String id;
	String address;
	String city;
	String review;
	ArrayList<Movie> movies;
	
	public Cinema() {}

	public Cinema(String name, String id, String address, String city, String review, ArrayList<Movie> movies) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
		this.city = city;
		this.review = review;
		this.movies = movies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	
}
