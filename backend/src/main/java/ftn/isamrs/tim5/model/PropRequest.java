package ftn.isamrs.tim5.model;

import org.hibernate.annotations.Cascade;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PropRequest")
public class PropRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @OneToOne
    Account userAccount;

    @OneToMany
    List<CineterAdmin> adminAccounts;

    @OneToOne(cascade = CascadeType.REMOVE)
    Props props;

    public PropRequest() {
    }

    public PropRequest(Long id, Account userAccount, Props props) {
        this.id = id;
        this.userAccount = userAccount;
        this.props = props;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public List<CineterAdmin> getAdminAccounts() {
        return adminAccounts;
    }

    public void setAdminAccounts(List<CineterAdmin> adminAccounts) {
        this.adminAccounts = adminAccounts;
    }

    public Props getProps() {
        return props;
    }

    public void setProps(Props props) {
        this.props = props;
    }
}
