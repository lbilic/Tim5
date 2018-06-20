package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.ShowReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowReservationRepository extends JpaRepository<ShowReservation, Long> {
    List<ShowReservation> findShowReservationsByPerformance_Id(long id);
}
