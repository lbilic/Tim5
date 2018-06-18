package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Performance;
import ftn.isamrs.tim5.model.ShowReservation;

import java.io.Serializable;

public class ShowReservationDTO implements Serializable {
    private Long id;
    private Performance performance;
    private float total_price;

    public ShowReservationDTO() {}

    public ShowReservationDTO(ShowReservation showReservation) {
        this.id = showReservation.getId();
        this.performance = showReservation.getPerformance();
        this.total_price = showReservation.getTotal_price();
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
