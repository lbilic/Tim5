package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Props;

import java.io.Serializable;
import java.util.Date;

public class PropsCreateDTO implements Serializable {
    private Long id;
    private String name;
    private float price;
    private String description;
    private int amount;
    private int version;
    private Long cineterId;
    private Date date;


    public PropsCreateDTO() {
    }

    public PropsCreateDTO(Long id, String name, float price, String description, int amount, int version, Date date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
        this.version = version;
        this.date = date;
    }

    public PropsCreateDTO(Props prop) {
        id = prop.getId();
        name = prop.getName();
        price = prop.getPrice();
        description = prop.getDescription();
        amount = prop.getAmount();
        version = prop.getVersion();
        cineterId = prop.getCineter().getId();
        date = prop.getDate();
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

    public Long getCineterId() {
        return cineterId;
    }

    public void setCineterId(Long cineterId) {
        this.cineterId = cineterId;
    }

    @Override
    public String toString() {
        return "PropsCreateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", version=" + version +
                ", cineterId=" + cineterId +
                ", date=" + date +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
