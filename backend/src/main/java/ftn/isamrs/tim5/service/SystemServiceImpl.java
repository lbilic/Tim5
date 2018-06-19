package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.repository.SystemRepository;
import ftn.isamrs.tim5.model.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemRepository systemRepository;

    @Override
    public List<System> findAll() {
        return this.systemRepository.findAll();
    }
}
