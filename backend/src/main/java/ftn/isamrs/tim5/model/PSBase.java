package ftn.isamrs.tim5.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PSBase")
@Inheritance(strategy=InheritanceType.JOINED)

// Base for performance and movie screening
public abstract class PSBase {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column (nullable = false)
    Date date;

    @Column(nullable = false)
    float price;

    @Column(nullable = false)
    @ElementCollection
    List<String> reservedSeats;
    //A1, A2, A3

    @ManyToOne(cascade=CascadeType.ALL)
    Hall hall;

    // dodati show
    @ManyToOne(cascade=CascadeType.ALL)
    Show show;

    @Column(nullable = false)
    String fastReservationSeats; // "A1" "A2"



    public PSBase () {}

    public PSBase(Date date, float price, Hall hall, Show show) {
        super();
        this.date = date;
        this.price = price;
        this.hall = hall;
        this.show = show;
    }

    public PSBase(Date date, float price, Hall hall) {
        this.date = date;
        this.price = price;
        this.hall = hall;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<String> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getFastReservationSeats() {
        return fastReservationSeats;
    }

    public void setFastReservationSeats(String fastReservationSeats) {
        this.fastReservationSeats = fastReservationSeats; }
}
