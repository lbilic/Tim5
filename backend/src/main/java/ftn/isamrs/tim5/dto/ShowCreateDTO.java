package ftn.isamrs.tim5.dto;

import java.io.Serializable;
import ftn.isamrs.tim5.model.Show;

public class ShowCreateDTO implements Serializable{

    private Long id;
    private String name;
    private String description;
    private boolean isMovie;
    private String director;
    private String runtime;
    private String genre;
    private String actors;
    private int version;

    public ShowCreateDTO() {
    }

    public ShowCreateDTO(Long id, String name, String description, boolean getIsMovie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isMovie = getIsMovie;
    }


     public ShowCreateDTO (Show show){
        this.id = show.getId();
        this.name=show.getName();
        this.description=show.getDescription();
        this.isMovie = show.getIsMovie();
        this.director = show.getDirector();
        this.runtime = show.getRuntime();
        this.genre=show.getGenre();
        this.actors =show.getActors();
        this.version = show.getVersion();
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

    public boolean getIsMovie() { return isMovie; }

    public void setIsMovie(boolean isMovie) { this.isMovie = isMovie; }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
