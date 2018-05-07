package ftn.isamrs.tim5.model;


import javax.persistence.*;

@Entity
@Table(name = "Props")
public class Props {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
	float price;

    @Column(nullable = false)
	String description;

    @ManyToOne
	Cineter cineter;
	
	public Props() {}

	public Props(String name, float price, String descripiton) {
		super();
		this.name = name;
		this.price = price;
		this.description = descripiton;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cineter getCineter() {
        return cineter;
    }

    public void setCineter(Cineter cineter) {
        this.cineter = cineter;
    }
}
