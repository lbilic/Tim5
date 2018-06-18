package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.repository.ShowReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowReservationServiceImpl implements ShowReservationService {

    @Autowired
    private ShowReservationRepository showReservationRepository;

    @Override
    public List<Show> findAll() {
        return this.showReservationRepository.findAll();
    }
}
