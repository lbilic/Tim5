package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name="moviereservation")
public class MovieReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @ManyToOne
    private MovieScreening screening;

    @Column
    private float total_price;

    public MovieReservation() {}

    public MovieReservation(MovieScreening screening, float total_price) {
        this.screening = screening;
        this.total_price = total_price;
    }

    public MovieReservation(int version, MovieScreening screening, float total_price) {
        this.version = version;
        this.screening = screening;
        this.total_price = total_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieScreening getScreening() {
        return screening;
    }

    public void setScreening(MovieScreening screening) {
        this.screening = screening;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
