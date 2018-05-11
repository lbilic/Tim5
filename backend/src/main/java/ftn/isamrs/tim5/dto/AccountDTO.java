package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.Account;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private boolean confirmed;

    public AccountDTO() {}

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.confirmed = account.isConfirmed();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public boolean isConfirmed() { return confirmed; }

    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }
}
