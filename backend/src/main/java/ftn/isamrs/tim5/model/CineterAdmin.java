package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name="Cineter_Admin")
public class CineterAdmin extends Account {

    @ManyToOne
    Cineter cineter;

    @Column
    private Boolean isFanZone;

    @Column
    private Boolean changedPassword;

    public CineterAdmin() {
    }

    public Cineter getCineter() {
        return cineter;
    }

    public void setCineter(Cineter cineter) {
        this.cineter = cineter;
    }

    public Boolean isFanZone() {
        return isFanZone;
    }

    public void setFanZone(boolean fanZone) {
        isFanZone = fanZone;
    }

    public boolean getChangedPassword() {
        return changedPassword;
    }

    public void setChangedPassword(boolean changedPassword) {
        this.changedPassword = changedPassword;
    }
}
