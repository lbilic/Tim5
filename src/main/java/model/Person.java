package model;

public abstract class Person {

	String name;
	String lastName;
	String password;
	String email;
	String number;
	
	public Person() {}

	public Person(String name, String lastName, String password, String email, String number) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
