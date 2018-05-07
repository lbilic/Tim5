package ftn.isamrs.tim5.dto;

public class CineterAdminCreateDTO {
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String number;
    private CineterCreateDTO cineter;


    public CineterAdminCreateDTO() {
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

    public CineterCreateDTO getCineter() {
        return cineter;
    }

    public void setCineter(CineterCreateDTO cineter) {
        this.cineter = cineter;
    }

    @Override
    public String toString() {
        return "CineterAdminCreateDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", cineter=" + cineter +
                '}';
    }
}
