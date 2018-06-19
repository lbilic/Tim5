package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.repository.MovieReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieReservationServiceImpl implements MovieReservationService {

    @Autowired
    private MovieReservationRepository movieReservationRepository;

    @Override
    public List<MovieReservation> findAll() {
        return this.movieReservationRepository.findAll();
    }

    @Override
    public List<MovieReservation> findMyReservationsForCineter(Long id, Long cineterId) {
        return movieReservationRepository.findMyReservationsForCineter(id, cineterId);
    }
}
