package ftn.isamrs.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ftn.isamrs.tim5.exception.BadRequestException;
import ftn.isamrs.tim5.exception.NotFoundException;
import ftn.isamrs.tim5.model.User;
import ftn.isamrs.tim5.repository.UserRepository;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(Long id) {
        User account = this.userRepository.findOne(id);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User account = this.userRepository.findByUsername(username);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public void checkUsername(String username) {
        User account = this.userRepository.findByUsername(username);
        if(account != null) throw new BadRequestException("Username is already used!");
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User account) {
        return this.userRepository.save(account);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Long id) {
        this.userRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUsernameTaken(String username) {
        User account = this.userRepository.findByUsername(username);
        return account != null;
    }
}
