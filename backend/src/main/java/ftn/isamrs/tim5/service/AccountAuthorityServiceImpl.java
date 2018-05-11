package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.AccountAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ftn.isamrs.tim5.repository.AccountAuthorityRepository;

@Service
public class AccountAuthorityServiceImpl implements AccountAuthorityService {

    @Autowired
    private AccountAuthorityRepository accountAuthorityRepository;


    @Override
    @Transactional(readOnly = false)
    public AccountAuthority save(AccountAuthority accountAuthority) {
        return this.accountAuthorityRepository.save(accountAuthority);
    }

    @Override
    @Transactional
    public void remove(Long id){
        this.accountAuthorityRepository.deleteById(id);
    }
}
