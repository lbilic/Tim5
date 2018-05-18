package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Props;

import java.io.Serializable;

public class PropsCreateDTO implements Serializable {
    private Long id;
    private String name;
    private float price;
    private String description;

    public PropsCreateDTO(String name, float price, String description, String cineterId) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public PropsCreateDTO(Long id, String name, float price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public PropsCreateDTO() {
    }

    public PropsCreateDTO(Props prop) {
        name = prop.getName();
        price = prop.getPrice();
        description = prop.getDescription();
        id = prop.getId();
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

    @Override
    public String toString() {
        return "PropsCreateDTO{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
