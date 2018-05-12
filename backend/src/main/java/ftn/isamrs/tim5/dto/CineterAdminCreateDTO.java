package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.CineterAdmin;

public class CineterAdminCreateDTO {
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String number;
    private String username;
    private CineterCreateDTO cineter;
    private Boolean isFanZone;
    private Boolean changedPassword;
    public CineterAdminCreateDTO() {
    }

    public CineterAdminCreateDTO(CineterAdmin admin) {

        name = admin.getName();
        lastName = admin.getLastName();
        password = admin.getPassword();
        email = admin.getEmail();
        //number = admin.getNumber();
        username = admin.getUsername();
        cineter = new CineterCreateDTO(admin.getCineter());
        isFanZone = admin.isFanZone();
        changedPassword = admin.getChangedPassword();

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

    public boolean isFanZone() {
        return isFanZone;
    }

    public void setFanZone(boolean fanZone) {
        isFanZone = fanZone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isChangedPassword() {
        return changedPassword;
    }

    public void setChangedPassword(boolean changedPassword) {
        this.changedPassword = changedPassword;
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
