package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.BoughtProps;

import java.io.Serializable;

public class BoughtPropDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private int amount;

    public BoughtPropDTO() {
    }

    public BoughtPropDTO(Long id, String name, String description, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public BoughtPropDTO(BoughtProps prop){
        this.id = prop.getId();
        this.name = prop.getName();
        this.description = prop.getDescription();
        this.amount = prop.getAmount();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
