package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MovieScreening")
public class MovieScreening extends PSBase {


    @Column(nullable = false)
    String type; // 2D/3D

	public MovieScreening() {}



	public MovieScreening(Date date, float price, String type, Hall hall) {
		super(date, price, hall);
		this.type = type;
	}
	public MovieScreening(Long id, Date date, float price, String type, Hall hall) {
		super(id, date, price, hall);
		this.type = type;
	}

	public String getType() { return type; }
	public void setType(String type) {
		this.type = type;
	}



	@Override
	public int hashCode() {
		return Objects.hash(getType());
	}
}
