package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cinema")
public class Cinema {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
    @Column(nullable = false)
	String name;
	@Column(nullable = false)
    String address;
	@Column(nullable = false)
	String city;
	@Column(nullable = false)
	String review;
	@OneToMany
	List<Movie> movies;
	
	public Cinema() {}

	public Cinema(String name, String address, String city, String review, List<Movie> movies) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.review = review;
		this.movies = movies;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
