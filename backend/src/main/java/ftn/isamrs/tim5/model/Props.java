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

    @Column(nullable = false)
	String cineterId;
	
	public Props() {}

	public Props(String name, float price, String description, String cineterId) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.cineterId = cineterId;
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

	public String getCineterId() {
		return cineterId;
	}

	public void setCineterId(String cineterId) {
		this.cineterId = cineterId;
	}

	@Override
	public String toString() {
		return "Props{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", cineterId='" + cineterId + '\'' +
				'}';
	}
}
