package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Bid;

import java.io.Serializable;

public class BidDTO implements Serializable {

    private Long id;
    private AccountDTO owner;
    private PropsCreateDTO prop;
    private int price;
    private AccountDTO bidder;
    private String status;

    public BidDTO(Long id, AccountDTO owner, PropsCreateDTO prop, int price, AccountDTO bidder, String status) {
        this.id = id;
        this.owner = owner;
        this.prop = prop;
        this.price = price;
        this.bidder = bidder;
        this.status = status;
    }

    public BidDTO(AccountDTO owner, PropsCreateDTO prop, int price, AccountDTO bidder, String status) {
        this.owner = owner;
        this.prop = prop;
        this.price = price;
        this.bidder = bidder;
        this.status = status;
    }

    public BidDTO(Bid dto){
        this.id = dto.getId();
        //this.owner = dto.getOwner();
        this.prop = new PropsCreateDTO(dto.getProp());
        this.price = dto.getPrice();
        this.bidder = new AccountDTO(dto.getBidder());
    }

    public BidDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDTO getOwner() {
        return owner;
    }

    public void setOwner(AccountDTO owner) {
        this.owner = owner;
    }

    public PropsCreateDTO getProp() {
        return prop;
    }

    public void setProp(PropsCreateDTO prop) {
        this.prop = prop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public AccountDTO getBidder() {
        return bidder;
    }

    public void setBidder(AccountDTO bidder) {
        this.bidder = bidder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
