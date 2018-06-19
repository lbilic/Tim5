package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Performance;
import java.io.Serializable;
import java.util.Date;

public class PerformanceCreateDTO implements Serializable {

    private Long id;
    private Date date;
    private float price;
    private Long hall;
    private ShowCreateDTO show;
    private String fastReservationSeats;

    public PerformanceCreateDTO(){}


    public PerformanceCreateDTO (Performance p){
        this.id = p.getId();
        this.date= p.getDate();
        this.hall= p.getHall().getId();
        this.price=p.getPrice();
        this.fastReservationSeats = p.getFastReservationSeats();
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

    public ShowCreateDTO getShow() {
        return show;
    }

    public void setShow(ShowCreateDTO show) {
        this.show = show;
    }

    public Long getHall() { return hall; }

    public void setHall(Long hall) { this.hall = hall; }

    public String getFastReservationSeats() {
        return fastReservationSeats;
    }

    public void setFastReservationSeats(String fastReservationSeats) {
        this.fastReservationSeats = fastReservationSeats;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
