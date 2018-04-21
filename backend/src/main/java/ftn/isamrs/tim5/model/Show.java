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

	@OneToMany(fetch = FetchType.LAZY)
    List<Performance> performances;
	
	public Show() {}

	public Show(String name, String description, List<Performance> performances) {
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

	public void setPerformances(List<Performance> performances) {
		this.performances = performances;
	}


	
}
