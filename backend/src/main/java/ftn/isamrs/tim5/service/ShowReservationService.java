package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Show;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowReservationService {
    List<Show> findAll();
}
