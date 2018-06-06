package ftn.isamrs.tim5.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Cineter_Admin")
public class CineterAdmin extends Account {

    @ManyToOne
    Cineter cineter;

    @Column
    private Boolean isFanZone;

    @Column
    private Boolean changedPassword;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<PropRequest> propRequests;

    public List<PropRequest> getPropRequests() {
        return propRequests;
    }

    public void setPropRequests(List<PropRequest> propRequests) {
        this.propRequests = propRequests;
    }

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
