package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.model.MovieScreening;
import ftn.isamrs.tim5.model.PSBase;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;

public class MovieScreeningCreateDTO {

    private Long id;
    private Date date;
    private float price;
    private Long hall;
    private String type;
    private ShowCreateDTO show;
    private String fastReservationSeats;

    public MovieScreeningCreateDTO(){
    }

    public MovieScreeningCreateDTO(Date date, float price, String type, HallCreateDTO hall) {
        this.date = date;
        this.price = price;
        this.hall = hall.getId();
        this.type = type;
        //this.show = show;
    }

    public MovieScreeningCreateDTO (MovieScreening ms){
        this.id = ms.getId();
        this.date = ms.getDate();
        this.hall = ms.getHall().getId();
        this.price= ms.getPrice();
        this.type= ms.getType();
        this.fastReservationSeats = ms.getFastReservationSeats();
    }

    public MovieScreeningCreateDTO (PSBase ms){
        this.id = ms.getId();
        this.date = ms.getDate();
        this.hall = ms.getHall().getId();
        this.price= ms.getPrice();
        this.fastReservationSeats = ms.getFastReservationSeats();
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getHall() {
        return hall;
    }

    public void setHall(Long hall) {
        this.hall = hall;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public ShowCreateDTO getShow() {
        return show;
    }

    public void setShow(ShowCreateDTO show) {
        this.show = show;
    }

    public String getFastReservationSeats() {
        return fastReservationSeats;
    }

    public void setFastReservationSeats(String fastReservationSeats) {
        this.fastReservationSeats = fastReservationSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {


        return "MovieScreeningCreateDTO{" +
                "date=" + date +
                ", price=" + price +
                ", hall=" + hall +
                ", type='" + type + '\'' +
                ", show=" + show +
                ", fastReservationSeats=" + this.fastReservationSeats +
                '}';
    }
}
