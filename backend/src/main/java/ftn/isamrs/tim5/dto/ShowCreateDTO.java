package ftn.isamrs.tim5.dto;

import java.io.Serializable;
import ftn.isamrs.tim5.model.Show;

public class ShowCreateDTO implements Serializable{

    private Long id;
    private String name;
    private String description;
    private boolean isMovie;

    public ShowCreateDTO() {
    }

    public ShowCreateDTO(String name, String description, boolean isMovie) {
        this.name = name;
        this.description = description;
        this.isMovie = isMovie;
    }


     public ShowCreateDTO (Show show){
        this.id = show.getId();
        this.name=show.getName();
        this.description=show.getDescription();
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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public boolean isMovie() { return isMovie; }

    public void setMovie(boolean movie) { isMovie = movie; }
}
