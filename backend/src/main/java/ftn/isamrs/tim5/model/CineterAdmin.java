package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name="Cineter_Admin")
public class CineterAdmin extends Account {

    @ManyToOne
    Cineter cineter;


    public CineterAdmin() {
    }

    public Cineter getCineter() {
        return cineter;
    }

    public void setCineter(Cineter cineter) {
        this.cineter = cineter;
    }


}
