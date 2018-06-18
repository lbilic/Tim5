package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieReservationRepository extends JpaRepository<MovieReservation, Long> {
}
