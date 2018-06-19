package ftn.isamrs.tim5.model;

import javax.persistence.*;

@Entity
@Table(name = "Bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne
    Props prop;

    @Column(nullable = false)
    int price;

    @OneToOne
    Account bidder;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int version;

    public Bid() {
    }

    public Bid(Props prop, int price, Account bidder) {
        this.prop = prop;
        this.price = price;
        this.bidder = bidder;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Props getProp() {
        return prop;
    }

    public void setProp(Props prop) {
        this.prop = prop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Account getBidder() {
        return bidder;
    }

    public void setBidder(Account bidder) {
        this.bidder = bidder;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
