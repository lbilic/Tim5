package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.model.ShowReservation;

public interface ShowReservationRepository {
    ShowReservation findById(Long id);

    ShowReservation findByShow(Show show);
}
