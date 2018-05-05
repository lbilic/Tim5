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
	String descripiton;
	
	public Props() {}

	public Props(String name, float price, String descripiton) {
		super();
		this.name = name;
		this.price = price;
		this.descripiton = descripiton;
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

	public String getDescripiton() {
		return descripiton;
	}

	public void setDescripiton(String descripiton) {
		this.descripiton = descripiton;
	}
	
	
	
}
