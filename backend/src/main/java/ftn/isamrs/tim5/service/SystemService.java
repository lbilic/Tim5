package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.System;

import java.util.List;

public interface SystemService {
    List<System> findAll();

    System save(System system);
}
