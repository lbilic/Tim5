package model;

import java.util.ArrayList;

public class Show {
	

	String name;
	String description;
	ArrayList<Performance> performances;
	
	public Show() {}

	public Show(String name, String description, ArrayList<Performance> performances) {
		super();
		this.name = name;
		this.description = description;
		this.performances = performances;
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

	public ArrayList<Performance> getPerformances() {
		return performances;
	}

	public void setPerformances(ArrayList<Performance> performances) {
		this.performances = performances;
	}
	
	
}
