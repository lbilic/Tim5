package ftn.isamrs.tim5.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @Column(nullable = false)
    int number;

    @Column(nullable = false)
    int rows;

    @Column(nullable = false)
    int columns;

    @ManyToOne
    Cineter cineter;

    Hall() {
    }

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
