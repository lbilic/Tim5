package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name="Cineter_Admin")
public class CineterAdmin extends Person {

    @ManyToOne
    Cineter cineter;

    @Column(nullable = false)
    boolean isFanZone;

    public CineterAdmin() {
    }

    public Cineter getCineter() {
        return cineter;
    }

    public void setCineter(Cineter cineter) {
        this.cineter = cineter;
    }

    public boolean getIsFanZone() { return isFanZone; }

    public void setIsFanZone(boolean isFanZone) {this.isFanZone = isFanZone; }
}
