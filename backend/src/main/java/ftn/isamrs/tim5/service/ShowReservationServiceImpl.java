package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.model.ShowReservation;
import ftn.isamrs.tim5.repository.ShowReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowReservationServiceImpl implements ShowReservationService {

    @Autowired
    private ShowReservationRepository showReservationRepository;

    @Override
    public List<ShowReservation> findAll() {
        return this.showReservationRepository.findAll();
    }

    @Override
    public List<ShowReservation> findMyReservations(Long id, Long cineterId) {
        return null;
    }

    @Override
    public List<ShowReservation> findShowReservationsByPerformance_Id(long id) {
        return this.showReservationRepository.findShowReservationsByPerformance_Id(id);
    }
}
