package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.model.MovieScreening;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isamrs.tim5.repository.MovieScreeningRepository;

@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

    @Autowired
    private MovieScreeningRepository movieScreeningRepository;

    @Autowired
    private ShowRepository showRepository;

    @Override
    public MovieScreening saveMovieScreening(MovieScreeningCreateDTO dto, Long id) {
        MovieScreening movieScreening =
                ConvertDTOToModel.convertMovieScreeningCreateDTOtoMovieScreening(dto);

        movieScreening.setShow(showRepository.findById(id).get());
        movieScreening = movieScreeningRepository.save(movieScreening);

        return movieScreening;
    }
}
