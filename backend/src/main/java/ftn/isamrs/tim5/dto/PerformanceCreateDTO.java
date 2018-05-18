package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.model.Hall;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

public class PerformanceCreateDTO implements Serializable {

    private Date date;
    private Time time;
    private float price;
   // private Hall hall;
    private ShowCreateDTO show;

    public PerformanceCreateDTO(){}

    public PerformanceCreateDTO(Date date, Time time, float price/*, Hall hall/*, ShowCreateDTO show*/) {
        this.date = date;
        this.time = time;
        this.price = price;
     //   this.hall = hall;
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

   /* public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }*/

    public ShowCreateDTO getShow() {
        return show;
    }

    public void setShow(ShowCreateDTO show) {
        this.show = show;
    }
}
