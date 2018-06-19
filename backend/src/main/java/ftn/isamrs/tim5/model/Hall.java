package ftn.isamrs.tim5.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    // number of hall
    @Column(nullable = false)
    int number;

    @Column(nullable = false)
    int rows;

    @Column(nullable = false)
    int columns;

    @ManyToOne
    @JsonBackReference
    Cineter cineter;

    Hall() {
    }
/*
    public Hall(int number, int rows, int columns, Cineter cineter) {
        this.number = number;
        this.rows = rows;
        this.columns = columns;
        this.cineter = cineter;
    }

    public Hall(int number, int rows, int columns) {
        this.number = number;
        this.rows = rows;
        this.columns = columns;
    }*/

    public int getRows() { return rows; }

    public void setRows(int rows) { this.rows = rows; }

    public int getColumns() { return columns; }

    public void setColumns(int columns) { this.columns = columns; }

    public Hall(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cineter getCineter() {
        return cineter;
    }

    public void setCineter(Cineter cineter) {
        this.cineter = cineter;
    }
}
