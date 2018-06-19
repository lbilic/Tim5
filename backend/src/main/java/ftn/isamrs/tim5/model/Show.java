package ftn.isamrs.tim5.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Show")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

    @Column( nullable = false)
	String name;
	@Column(nullable = false)
    String description;

	@Column
	String director;

	@Column
	String runtime;

	@Column
	String genre;

	@Column
	String stars;

	@Column (nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
	boolean isMovie;

	@OneToMany(mappedBy = "show")
	List<Review> reviews;

	@OneToMany(fetch = FetchType.LAZY)
    List<Performance> performances;

	@ManyToOne
	@JsonBackReference
	Cineter cineter;

	@Version
	@Column(nullable = false, columnDefinition = "integer default 0")
	private int version;

	public Show() {}

	/*
	public Show(String name, String description, boolean getIsMovie, List<Performance> performances) {
		super();
		this.name = name;
		this.description = description;
		this.performances = performances;
		this.getIsMovie = getIsMovie;

	}
*/

	public Show(String name, String description, String director,
				String runtime, String genre, String stars, boolean isMovie) {
		this.name = name;
		this.description = description;
		this.director = director;
		this.runtime = runtime;
		this.genre = genre;
		this.stars = stars;
		this.isMovie = isMovie;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public boolean getIsMovie() { return isMovie; }

	public void setIsMovie(boolean movie) { isMovie = movie; }

	public List<Performance> getPerformances() {
		return performances;
	}

	public void setPerformances(List<Performance> performances) {
		this.performances = performances;
	}

	public Cineter getCineter() {
		return cineter;
	}

	public void setCineter(Cineter cineter) {
		this.cineter = cineter;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
