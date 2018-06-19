package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Props;

import java.io.Serializable;

public class PropsCreateDTO implements Serializable {
    private Long id;
    private String name;
    private float price;
    private String description;
    private int amount;
    private int version;


    public PropsCreateDTO() {
    }

    public PropsCreateDTO(Long id, String name, float price, String description, int amount, int version) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
        this.version = version;
    }

    public PropsCreateDTO(Props prop) {
        id = prop.getId();
        name = prop.getName();
        price = prop.getPrice();
        description = prop.getDescription();
        amount = prop.getAmount();
        version = prop.getVersion();

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PropsCreateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
