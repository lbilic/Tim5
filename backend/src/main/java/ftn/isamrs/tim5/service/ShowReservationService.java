package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.ShowReservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowReservationService {
    List<ShowReservation> findAll();

    List<ShowReservation> findMyReservations(Long id, Long cineterId);

    List<ShowReservation> findShowReservationsByPerformance_Id(long id);
}
