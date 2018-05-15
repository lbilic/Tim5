package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Performance")
public class Performance {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Column //(nullable = false)
	Date date;
	@Column
	String seatLayout;
	@Column(nullable = false)
	float price;
	@Column(nullable = false)
	String hall;

	@ManyToOne
    private Show show;
	
	public Performance() {}

	public Performance(Date date, String seatLayout, float price, String hall, Show show) {
		super();
		this.date = date;
		this.seatLayout = seatLayout;
		this.price = price;
		this.hall = hall;
		this.show = show;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Show getShow() { return show; }

	public void setShow(Show show) { this.show = show; }
}
