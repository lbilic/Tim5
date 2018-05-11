package ftn.isamrs.tim5.service;


import ftn.isamrs.tim5.model.Authority;

public interface AuthorityService {

    Authority findByName(String name);
}
