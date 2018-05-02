package ftn.isamrs.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ftn.isamrs.tim5.exception.BadRequestException;
import ftn.isamrs.tim5.exception.NotFoundException;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.repository.AccountRepository;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> findAll(Pageable page) {
        return this.accountRepository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findOne(Long id) {
        Account account = this.accountRepository.getOne(id);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Account findByUsername(String username) {
        Account account = this.accountRepository.findByUsername(username);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public void checkUsername(String username) {
        Account account = this.accountRepository.findByUsername(username);
        if(account != null) throw new BadRequestException("Username is already used!");
    }

    @Override
    @Transactional(readOnly = false)
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Long id) {
        this.accountRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUsernameTaken(String username) {
        Account account = this.accountRepository.findByUsername(username);
        return account != null;
    }
}
