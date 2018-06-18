package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.Show;

public interface MovieReservationRepository {
    MovieReservation findById(Long id);

    MovieReservation findByMovie(Show movie);
}
