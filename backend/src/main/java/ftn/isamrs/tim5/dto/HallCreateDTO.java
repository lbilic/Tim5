package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Hall;


import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class HallCreateDTO implements Serializable {

    private int number;
    private int rows;
    private int columns;

    public HallCreateDTO() {
    }

    public HallCreateDTO(int number, int rows, int columns) {
        this.number = number;
        this.rows = rows;
        this.columns = columns;
    }

    public HallCreateDTO(Hall hall) {
        this.number = hall.getNumber();
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
}
