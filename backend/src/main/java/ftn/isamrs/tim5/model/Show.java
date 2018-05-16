package ftn.isamrs.tim5.model;


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

	@Column (nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
	boolean isMovie;

	@OneToMany(fetch = FetchType.LAZY)
    List<Performance> performances;

	@ManyToOne
	Cineter cineter;
	
	public Show() {}

	public Show(String name, String description, boolean isMovie, List<Performance> performances) {
		super();
		this.name = name;
		this.description = description;
		this.performances = performances;
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

	public boolean isMovie() { return isMovie; }

	public void setMovie(boolean movie) { isMovie = movie; }

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
}
