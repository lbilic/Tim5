package ftn.isamrs.tim5.dto;

import java.io.Serializable;

public class ProfileChangeDTO implements Serializable {

    private String name;
    private String lastName;

    public ProfileChangeDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public ProfileChangeDTO() {
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

    @Override
    public String toString() {
        return "ProfileChangeDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}