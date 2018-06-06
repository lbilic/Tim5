package ftn.isamrs.tim5.model;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;
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
    List<Integer> seatLayout;
    //0 0 1 0 0 0 0 0 0 0 0

    @OneToOne
    Hall hall;

    // dodati show
    @ManyToOne
    Show show;

    @Column(nullable = false)
    @ElementCollection
    List<Integer> fastReservationIndex; // 2 3 5



    public PSBase () {}

    public PSBase(Date date, float price, Hall hall, Show show) {
        super();
        this.date = date;
        this.price = price;
        //this.hall = hall;
        //this.show = show;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(List<Integer> seatLayout) {
        this.seatLayout = seatLayout;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Integer> getFastReservationIndex() {
        return fastReservationIndex;
    }

    public void setFastReservationIndex(List<Integer> fastReservationIndex) {
        this.fastReservationIndex = fastReservationIndex;
    }
}
