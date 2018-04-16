package model;

import java.util.ArrayList;

public class Movie {
	
	String name;
	String description;
	ArrayList<MovieScreening> screenings;
	
	public Movie(){
		// TODO Auto-generated constructor stub
	}
	
	

	public Movie(String name, String description, ArrayList<MovieScreening> screenings) {
		super();
		this.name = name;
		this.description = description;
		this.screenings = screenings;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<MovieScreening> getScreenings() {
		return screenings;
	}

	public void setScreenings(ArrayList<MovieScreening> screenings) {
		this.screenings = screenings;
	}
	
	
	
}
