package ftn.isamrs.tim5.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne()
	Cineter cineter;

    @Column(nullable = false)
	int amount;

    @ManyToOne
	Account account;

	@Version
	@Column(nullable = false, columnDefinition = "integer default 0")
	private int version;

	@OneToOne(mappedBy = "props", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private PropRequest request;

	@Column(nullable = false)
	Date date;


	public Props() {}

	public Props(String name, float price, String descripiton, int amount, Date date
	) {
		super();
		this.name = name;
		this.price = price;

		this.description = descripiton;
		this.amount = amount;
		this.date = date;
	}

	public Props(String name, float price, String description, Cineter cineter, int amount, Account account, int version) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.cineter = cineter;
		this.amount = amount;
		this.account = account;
		this.version = version;
	}

		public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public PropRequest getRequest() {
		return request;
	}

	public void setRequest(PropRequest request) {
		this.request = request;
	}
}
