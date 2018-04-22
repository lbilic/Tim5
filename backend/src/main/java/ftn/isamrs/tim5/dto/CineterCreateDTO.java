package ftn.isamrs.tim5.dto;


import java.io.Serializable;

public class CineterCreateDTO implements Serializable {

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
