package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.model.ShowReservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ShowReservationService {
    List<Show> findAll();

    List<ShowReservation> findMyReservations(Long id, Long cineterId);
}
