package ftn.isamrs.tim5.dto;

public class ReviewDTO {

    double rate;

    public ReviewDTO(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
