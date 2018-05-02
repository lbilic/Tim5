package ftn.isamrs.tim5.dto;

import java.io.Serializable;

public class ShowCreateDTO implements Serializable{

    private String name;
    private String description;

    public ShowCreateDTO() {
    }

    public ShowCreateDTO(String name, String description) {
        this.name = name;
        this.description = description;
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
}
