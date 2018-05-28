package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Performance")
public class Performance extends PSBase {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	public Performance() { }

	public Performance(Date date, float price , Hall hall) {
		super(date, price , hall);
	}

}
