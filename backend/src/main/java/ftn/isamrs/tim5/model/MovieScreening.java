package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "MovieScreening")
public class MovieScreening extends PSBase {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

    @Column(nullable = false)
    String type; // 2D/3D

	public MovieScreening() {}

	public MovieScreening(Date date, float price, String type, Hall hall) {
		super(date, price, hall);
		this.type = type;
	}

	public String getType() { return type; }
	public void setType(String type) {
		this.type = type;
	}

}
