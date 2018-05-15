package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Show;

import java.io.Serializable;
import java.util.Date;

public class PerformanceCreateDTO implements Serializable {

    private Date date;
    private String seatLayout;
    private float price;
    private String hall;
    private ShowCreateDTO show;

    public PerformanceCreateDTO(){}

    public PerformanceCreateDTO(Date date, String seatLayout, float price, String hall, ShowCreateDTO show) {
        this.date = date;
        this.seatLayout = seatLayout;
        this.price = price;
        this.hall = hall;
        this.show = show;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(String seatLayout) {
        this.seatLayout = seatLayout;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public ShowCreateDTO getShow() {
        return show;
    }

    public void setShow(ShowCreateDTO show) {
        this.show = show;
    }
}
