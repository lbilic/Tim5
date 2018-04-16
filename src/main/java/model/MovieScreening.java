package model;

import java.sql.Time;
import java.util.Date;

public class MovieScreening {

	Date date;
	Time time;
	String seatLayout;
	float price;
	String hall;
	String type; // 2d/3d
	
	public MovieScreening() {}

	public MovieScreening(Date date, Time time, String seatLayout, float price, String hall, String type) {
		super();
		this.date = date;
		this.time = time;
		this.seatLayout = seatLayout;
		this.price = price;
		this.hall = hall;
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getSeatLayout() {
		return seatLayout;
	}

	public void setSeatLayout(String seatLayout) {
		this.seatLayout = seatLayout;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
