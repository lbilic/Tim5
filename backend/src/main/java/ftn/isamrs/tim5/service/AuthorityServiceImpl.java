package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.exception.NotFoundException;
import ftn.isamrs.tim5.model.Authority;
import ftn.isamrs.tim5.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    @Transactional(readOnly = true)
    public Authority findByName(String name) {
        Authority authority = this.authorityRepository.findByName(name);
        if(authority == null) throw new NotFoundException("Authority with name: " + name + " not found!");
        return authority;
    }
}
