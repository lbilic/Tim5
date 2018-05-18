package ftn.isamrs.tim5.model;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;

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
/*
    @OneToOne
    Hall hall;*/

    // dodati show

    public PSBase () {}

    public PSBase(Date date, float price/*, Hall hall, Show show*/) {
        super();
        this.date = date;
        this.price = price;
        //this.hall = hall;
        //this.show = show;
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

   /* public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
