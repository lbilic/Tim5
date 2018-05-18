package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService{

    private HallRepository hallRepository;

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }
}
