package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.Show;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieReservationService {
    List<MovieReservation> findAll();

    List<MovieReservation> findMyReservationsForCineter(Long id, Long cineterId);
}
