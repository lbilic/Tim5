package ftn.isamrs.tim5.dto;
import ftn.isamrs.tim5.model.Hall;


import java.io.Serializable;

public class HallCreateDTO implements Serializable
{
    private int number;
    private String seatLayout;


    public HallCreateDTO (){}

    public HallCreateDTO(int number, String seatLayout) {
        this.number = number;
        this.seatLayout = seatLayout;
    }

    public HallCreateDTO (Hall hall){
        this.number = hall.getNumber();
        this.seatLayout = hall.getSeatLayout();
    }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public String getSeatLayout() { return seatLayout; }

    public void setSeatLayout(String seatLayout) { this.seatLayout = seatLayout; }
}
