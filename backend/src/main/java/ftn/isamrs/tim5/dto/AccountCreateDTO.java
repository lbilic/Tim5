package ftn.isamrs.tim5.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AccountCreateDTO implements Serializable {
    @Valid
    @NotNull
    private LoginDTO loginAccount;
    @NotNull
    private String name;
    private String lastName;
    @NotNull
    private String email;

    public AccountCreateDTO() { }

    public AccountCreateDTO(LoginDTO loginAccount, String name, String lastName, String email) {
        this.loginAccount = loginAccount;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public LoginDTO getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginDTO loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
