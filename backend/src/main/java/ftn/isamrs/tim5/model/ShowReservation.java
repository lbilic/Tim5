package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name="showreservation")
public class ShowReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @ManyToOne
    Performance performance;

    @Column
    float total_price;

    public ShowReservation() {}

    public ShowReservation(Performance performance, float total_price) {
        this.performance = performance;
        this.total_price = total_price;
    }

    public ShowReservation(int version, Performance performance, float total_price) {
        this.version = version;
        this.performance = performance;
        this.total_price = total_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
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
