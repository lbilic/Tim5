package ftn.isamrs.tim5.dto;

import ftn.isamrs.tim5.model.PropRequest;

import java.io.Serializable;

public class PropsRequestDTO implements Serializable {

    private Long id;
    private AccountDTO account;
    private PropsCreateDTO props;

    public PropsRequestDTO() {
    }

    public PropsRequestDTO(PropRequest request){
        this.id = request.getId();
        this.account = new AccountDTO(request.getUserAccount());
        this.props = new PropsCreateDTO(request.getProps());
    }

    public PropsRequestDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public PropsCreateDTO getProps() {
        return props;
    }

    public void setProps(PropsCreateDTO props) {
        this.props = props;
    }
}


