package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Props;

public interface PropsService {

    Props saveProps(PropsCreateDTO props, Account account);
}
