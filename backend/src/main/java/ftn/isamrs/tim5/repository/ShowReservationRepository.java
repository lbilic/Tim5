package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.model.ShowReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowReservationRepository extends JpaRepository<Show, Long> {
}
