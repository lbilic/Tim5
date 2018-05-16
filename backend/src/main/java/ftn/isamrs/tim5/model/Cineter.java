package ftn.isamrs.tim5.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cineter")
public class Cineter {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	String address;

	@Column(nullable = false)
	String city;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cineter")
	List<Review> review;

	@OneToMany(mappedBy = "cineter")
	List<Show> shows;

	@OneToMany(mappedBy = "cineter")
    List<CineterAdmin> cineterAdmin;

	@OneToMany(mappedBy = "cineter")
	List<Props> props;

	@Column
    boolean isTheater;

	@Column
	double score;


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
	
	public Cineter() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

	public List<CineterAdmin> getCineterAdmin() {
		return cineterAdmin;
	}

	public void setCineterAdmin(List<CineterAdmin> cineterAdmin) {
		this.cineterAdmin = cineterAdmin;
	}

	public boolean isTheater() {
        return isTheater;
    }

    public void setTheater(boolean theater) {
        isTheater = theater;
    }

	public List<Props> getProps() {
		return props;
	}

	public void setProps(List<Props> props) {
		this.props = props;
	}
}
