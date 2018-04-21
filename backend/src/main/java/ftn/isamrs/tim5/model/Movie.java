package ftn.isamrs.tim5.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	String description;
	@OneToMany
	List<Performance> performances;
	
	public Movie(){
		// TODO Auto-generated constructor stub
	}

	public Movie(String name, String description, List<Performance> performances) {
		super();
		this.name = name;
		this.description = description;
		this.performances = performances;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
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

	public List<Performance> getPerformances() {
		return performances;
	}


	
	
	
}
