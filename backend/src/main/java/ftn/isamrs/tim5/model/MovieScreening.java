package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "MovieScreening")
public class MovieScreening {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column //(nullable = false)
	Date date;
	@Column(nullable = false)
	String seatLayout;

	@Column(nullable = false)
	float price;
    @Column(nullable = false)
    String hall;
    @Column(nullable = false)
    String type; // 2d/3d

	@ManyToOne
	private Show show;

	public MovieScreening() {}

	public MovieScreening(Date date, Time time, String seatLayout, float price, String hall, String type) {
		super();
		this.date = date;
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
