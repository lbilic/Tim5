package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.model.MovieScreening;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isamrs.tim5.repository.MovieScreeningRepository;

@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

    @Autowired
    private MovieScreeningRepository movieScreeningRepository;

    @Override
    public MovieScreening saveMovieScreening(MovieScreeningCreateDTO dto) {
        MovieScreening movieScreening =
                ConvertDTOToModel.convertMovieScreeningCreateDTOtoMovieScreening(dto);

        movieScreening = movieScreeningRepository.save(movieScreening);
        return movieScreening;
    }
}
