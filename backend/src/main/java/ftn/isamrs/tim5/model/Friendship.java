package ftn.isamrs.tim5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ftn.isamrs.tim5.model.enumeration.FriendshipStatus;

import javax.persistence.*;

@Entity
@Table(name = "friendship")
@Inheritance(strategy=InheritanceType.JOINED)
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @OneToOne
    Account sender;

    @OneToOne
    Account receiver;

    @Column
    FriendshipStatus status;

    public Friendship() {}

    public Friendship(Account sender, Account receiver, FriendshipStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public Friendship(int version, Account sender, Account receiver, FriendshipStatus status) {
        this.version = version;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
