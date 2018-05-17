package ftn.isamrs.tim5.dto;


import ftn.isamrs.tim5.model.Cineter;

import java.io.Serializable;

public class CineterCreateDTO implements Serializable {

    private Long id;
    private String name;
    private String address;
    private String city;
    private boolean isTheater;

    public CineterCreateDTO(String name, String address, String city, boolean isTheater) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.isTheater = isTheater;
    }

    public CineterCreateDTO() {
    }

    public CineterCreateDTO(Cineter c) {

        id=c.getId();
        name = c.getName();
        address = c.getAddress();
        city = c.getCity();
        isTheater = c.isTheater();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean getIsTheater() {
        return isTheater;
    }

    public void setIsTheater(boolean theater) {
        isTheater = theater;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public boolean isTheater() { return isTheater; }

    public void setTheater(boolean theater) { isTheater = theater; }

    @Override
    public String toString() {
        return "CineterCreateDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", isTheater=" + isTheater +
                '}';
    }
}
