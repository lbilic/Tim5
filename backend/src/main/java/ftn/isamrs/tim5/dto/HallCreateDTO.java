package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Hall;


import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class HallCreateDTO implements Serializable {

    private Long id;
    private int number;
    private int rows;
    private int columns;

    private CineterCreateDTO cineter;

    public HallCreateDTO() {
    }
/*
    public HallCreateDTO(int number, int rows, int columns) {
        this.number = number;
        this.rows = rows;
        this.columns = columns;
    }
*/
    public HallCreateDTO(Hall hall) {
        this.id= hall.getId();
        this.number = hall.getNumber();
        this.rows = hall.getRows();
        this.columns = hall.getColumns();
        this.cineter = new CineterCreateDTO(hall.getCineter());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public CineterCreateDTO getCineter() { return cineter; }

    public void setCineter(CineterCreateDTO cineter) { this.cineter = cineter; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
