package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.model.MovieScreening;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieScreeningService {
    MovieScreening saveMovieScreening(MovieScreeningCreateDTO dto, Long id, String time);

    List<MovieScreening> findAllByShowId (Long id);
    List<MovieScreening> findAll();
}
