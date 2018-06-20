package ftn.isamrs.tim5.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cineter", cascade = CascadeType.REMOVE)
	List<Review> review;

	@OneToMany(mappedBy = "cineter", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	List<Show> shows;

	@OneToMany(mappedBy = "cineter", cascade = CascadeType.REMOVE)
	@JsonManagedReference
    List<CineterAdmin> cineterAdmin;

	@OneToMany(mappedBy = "cineter", cascade = CascadeType.REMOVE)
	List<Props> props;

	@OneToMany(mappedBy = "cineter", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	List<Hall> halls;

	@Column
    boolean isTheater;

	@Column
	double score;

	@OneToMany
	private List<Review> reviews;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
	
	public Cineter() {}

	public Cineter(String name, String address, String city, List<Props> props, boolean isTheater, double score) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.props = props;
		this.isTheater = isTheater;
		this.score = score;
	}
	public Cineter(Long id, String name, String address, String city, List<Props> props, boolean isTheater, double score) {
		this.id = id;
    	this.name = name;
		this.address = address;
		this.city = city;
		this.props = props;
		this.isTheater = isTheater;
		this.score = score;
	}

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

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
