package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.AccountAuthority;

public interface AccountAuthorityService {

    AccountAuthority save(AccountAuthority accountAuthority);

    void remove(Long id);

    int AuthorityByAccId(Long id);


}
