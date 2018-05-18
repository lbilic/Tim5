package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Hall;

import java.util.Date;
import java.sql.Time;

public class MovieScreeningCreateDTO {

    private Date date;
    private Time time;
    private float price;
    private Hall hall;
    private String type;
    private ShowCreateDTO show;

    public MovieScreeningCreateDTO(){}

    public MovieScreeningCreateDTO(Date date, Time time, float price, Hall hall, String type/*, ShowCreateDTO show*/) {
        this.date = date;
        this.time = time;
        this.price = price;
        this.hall = hall;
        this.type = type;
        //this.show = show;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() { return time; }

    public void setTime(Time time) { this.time = time; }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
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
}
