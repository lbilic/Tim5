package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name="ShowReservation")
public class ShowReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    Performance performance;

    @Column
    float total_price;

    public ShowReservation() {}

    public ShowReservation(Performance performance, float total_price) {
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
}
