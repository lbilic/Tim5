package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Hall;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HallService {

    List <Hall> findAll();
}
