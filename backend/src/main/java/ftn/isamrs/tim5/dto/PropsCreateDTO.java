package ftn.isamrs.tim5.dto;

import java.io.Serializable;

public class PropsCreateDTO implements Serializable {
    private String name;
    private float price;
    private String description;

    public PropsCreateDTO(String name, float price, String description, String cineterId) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public PropsCreateDTO() {
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



    @Override
    public String toString() {
        return "PropsCreateDTO{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
