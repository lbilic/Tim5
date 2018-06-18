package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.MovieScreening;

import java.io.Serializable;

public class MovieReservationDTO implements Serializable {
    private Long id;
    private MovieScreening screening;
    private float total_price;

    public MovieReservationDTO() {}

    public MovieReservationDTO(MovieReservation movieReservation) {
        this.id = movieReservation.getId();
        this.screening = movieReservation.getScreening();
        this.total_price = movieReservation.getTotal_price();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieScreening getScreening() {
        return screening;
    }

    public void setScreening(MovieScreening screening) {
        this.screening = screening;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }
}
