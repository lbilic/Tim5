package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name = "Hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @Column (nullable = false)
    int number;

    @Column (nullable = false)
    String seatLayout;

    Hall (){}

    public Hall(int number, String seatLayout) {
        this.number = number;
        this.seatLayout = seatLayout;
    }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSeatLayout() { return seatLayout; }

    public void setSeatLayout(String seatLayout) { this.seatLayout = seatLayout; }
}
