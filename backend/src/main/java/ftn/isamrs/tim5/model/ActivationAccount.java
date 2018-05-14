package ftn.isamrs.tim5.model;

import java.io.Serializable;

public class ActivationAccount implements Serializable {
    private String activationId;

    public ActivationAccount() {}

    public ActivationAccount(String activationId) {
        this.activationId = activationId;
    }

    public String getActivationId() {
        return activationId;
    }

    public void setActivationId(String activationId) {
        this.activationId = activationId;
    }
}
