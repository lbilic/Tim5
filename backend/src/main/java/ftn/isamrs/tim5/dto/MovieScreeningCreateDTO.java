package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.model.MovieScreening;

import java.util.Date;
import java.sql.Time;

public class MovieScreeningCreateDTO {

    private Date date;
    private Time time;
    private float price;
    private HallCreateDTO hall;
    private String type;
    private ShowCreateDTO show;

    public MovieScreeningCreateDTO(){}

   /* public MovieScreeningCreateDTO(Date date, float price, String type, HallCreateDTO hall/) {
        this.date = date;
        this.price = price;
        this.hall = hall;
        this.type = type;
        //this.show = show;
    }*/

    public MovieScreeningCreateDTO (MovieScreening ms){
        this.date = ms.getDate();
        this.hall = new HallCreateDTO(ms.getHall());
        this.price= ms.getPrice();
        this.type= ms.getType();

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

    public HallCreateDTO getHall() {
        return hall;
    }

    public void setHall(HallCreateDTO hall) {
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
