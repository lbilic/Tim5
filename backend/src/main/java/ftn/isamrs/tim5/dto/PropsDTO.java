package ftn.isamrs.tim5.dto;

import java.util.Date;

public class PropsDTO {


    private Long id;
    private String name;
    private float price;
    private String description;
    private int amount;
    private int version;
    private Date date;


    public PropsDTO() {
    }


    public PropsDTO(Long id, String name, float price, String description, int amount, int version, Date date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
        this.version = version;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PropsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", version=" + version +
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
