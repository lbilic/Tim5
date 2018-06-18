package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService{

    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }
}
