package model;

import java.util.ArrayList;

public class Theater {

	String name;
	String id;
	String address;
	String city;
	String review;
	ArrayList<Show> shows;
	
	public Theater() {}
	
	

	public Theater(String name, String id, String address, String city, String review, ArrayList<Show> shows) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
		this.city = city;
		this.review = review;
		this.shows = shows;
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

	public ArrayList<Show> getShows() {
		return shows;
	}

	public void setShows(ArrayList<Show> shows) {
		this.shows = shows;
	}
	
	
}
