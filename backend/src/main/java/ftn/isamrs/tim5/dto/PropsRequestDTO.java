package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.PropRequest;

import java.io.Serializable;

public class PropsRequestDTO implements Serializable {

    private Long id;

    public PropsRequestDTO() {
    }

    public PropsRequestDTO(PropRequest request){
        this.id = request.getId();
    }

    public PropsRequestDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

