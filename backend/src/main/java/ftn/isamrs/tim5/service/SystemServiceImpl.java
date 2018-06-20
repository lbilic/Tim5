package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.repository.SystemRepository;
import ftn.isamrs.tim5.model.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemRepository systemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<System> findAll() {
        return this.systemRepository.findAll();
    }

    @Override
    @Transactional
    public System save(System system) {
        return this.systemRepository.save(system);
    }
}
