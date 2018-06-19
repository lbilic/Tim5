package ftn.isamrs.tim5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Inheritance(strategy=InheritanceType.JOINED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String lastName;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean confirmed;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String activationId;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Props> props;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BoughtProps> boughtProps;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<AccountAuthority> accountAuthorities;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<MovieReservation> movieReservations;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ShowReservation> showReservations;

    public Account(String username, String password, int version, boolean deleted, String name,
                   String lastName, String email, String activationId, boolean confirmed) {
        this.username = username;
        this.version = version;
        this.deleted = deleted;
        this.name = name;
        this.lastName = lastName;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.email = email;
        //this.number = number;
        this.confirmed = confirmed;
        this.activationId = activationId;
    }

    public Account() {
        this.accountAuthorities = new ArrayList<>();
        this.showReservations = new ArrayList<>();
        this.movieReservations = new ArrayList<>();
        this.confirmed = false;
        this.props = new ArrayList<Props>();
        this.boughtProps = new ArrayList<BoughtProps>();
    }

    public Account(String username, String password) {
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.accountAuthorities = new ArrayList<>();
        this.showReservations = new ArrayList<>();
        this.movieReservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }*/

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public int getVersion() { return version; }

    public void setVersion(int version) { this.version = version; }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public List<AccountAuthority> getAccountAuthorities() { return accountAuthorities; }

    public void setAccountAuthorities(List<AccountAuthority> accountAuthorities) { this.accountAuthorities = accountAuthorities; }

    public boolean isConfirmed() { return confirmed; }

    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    public String getActivationId() { return activationId; }

    public void setActivationId(String activationID) { this.activationId = activationID; }

    public List<Props> getProps() {
        return props;
    }

    public void setProps(List<Props> props) {
        this.props = props;
    }

    public List<BoughtProps> getBoughtProps() {
        return boughtProps;
    }

    public void setBoughtProps(List<BoughtProps> boughtProps) {
        this.boughtProps = boughtProps;
    }

    public List<MovieReservation> getMovieReservations() { return movieReservations; }

    public void setMovieReservations(List<MovieReservation> movieReservations) { this.movieReservations = movieReservations; }

    public List<ShowReservation> getShowReservations() { return showReservations; }

    public void setShowReservations(List<ShowReservation> showReservations) { this.showReservations = showReservations; }
}
